WITH user_totals AS (
    SELECT 
        user_id,
        COUNT(DISTINCT content_id) AS total_reactions
    FROM reactions
    GROUP BY user_id
    HAVING COUNT(DISTINCT content_id) >= 5
),
reaction_counts AS (
    SELECT 
        user_id,
        reaction,
        COUNT(*) AS reaction_count
    FROM reactions
    GROUP BY user_id, reaction
)
SELECT 
    ut.user_id,
    rc.reaction AS dominant_reaction,
    ROUND(rc.reaction_count / ut.total_reactions, 2) AS reaction_ratio
FROM user_totals ut
JOIN reaction_counts rc 
    ON ut.user_id = rc.user_id
WHERE rc.reaction_count / ut.total_reactions >= 0.60
ORDER BY reaction_ratio DESC, ut.user_id ASC;