# Write your MySQL query statement below
WITH RankedEvents AS (
    SELECT 
        user_id,
        event_date,
        event_type,
        plan_name,
        monthly_amount,
        ROW_NUMBER() OVER (PARTITION BY user_id ORDER BY event_date DESC, event_id DESC) AS rn_desc,
        MIN(event_date) OVER (PARTITION BY user_id) AS first_event_date,
        MAX(event_date) OVER (PARTITION BY user_id) AS last_event_date,
        MAX(monthly_amount) OVER (PARTITION BY user_id) AS max_historical_amount,
        SUM(CASE WHEN event_type = 'downgrade' THEN 1 ELSE 0 END) OVER (PARTITION BY user_id) AS downgrade_count
    FROM subscription_events
)
SELECT 
    user_id,
    plan_name AS current_plan,
    monthly_amount AS current_monthly_amount,
    max_historical_amount,
    DATEDIFF(last_event_date, first_event_date) AS days_as_subscriber
FROM RankedEvents
WHERE rn_desc = 1
  AND event_type != 'cancel'
  AND downgrade_count >= 1
  AND monthly_amount < 0.5 * max_historical_amount
  AND DATEDIFF(last_event_date, first_event_date) >= 60
ORDER BY days_as_subscriber DESC, user_id ASC;