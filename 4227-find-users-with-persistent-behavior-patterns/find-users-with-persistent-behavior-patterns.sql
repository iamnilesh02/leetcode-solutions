WITH single_action_days AS (
    SELECT 
        user_id,
        action_date,
        MAX(action) AS action
    FROM activity
    GROUP BY user_id, action_date
    HAVING COUNT(*) = 1
),
streaks AS (
    SELECT 
        user_id,
        action,
        action_date,
        DATE_SUB(action_date, INTERVAL ROW_NUMBER() OVER (PARTITION BY user_id, action ORDER BY action_date) DAY) AS grp
    FROM single_action_days
),
grouped_streaks AS (
    SELECT 
        user_id,
        action,
        COUNT(*) AS streak_length,
        MIN(action_date) AS start_date,
        MAX(action_date) AS end_date
    FROM streaks
    GROUP BY user_id, action, grp
    HAVING COUNT(*) >= 5
),
ranked_users AS (
    SELECT 
        user_id,
        action,
        streak_length,
        start_date,
        end_date,
        ROW_NUMBER() OVER (
            PARTITION BY user_id 
            ORDER BY streak_length DESC, action ASC, start_date DESC
        ) AS rn
    FROM grouped_streaks
)
SELECT 
    user_id,
    action,
    streak_length,
    start_date,
    end_date
FROM ranked_users
WHERE rn = 1
ORDER BY streak_length DESC, user_id ASC;
