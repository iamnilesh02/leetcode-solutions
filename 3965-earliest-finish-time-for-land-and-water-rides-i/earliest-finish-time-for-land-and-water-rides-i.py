class Solution:
    def earliestFinishTime(self, landStartTime: List[int], landDuration: List[int], waterStartTime: List[int], waterDuration: List[int]) -> int:
        min_land = min(s + d for s, d in zip(landStartTime, landDuration))
        land_first = min(max(min_land, ws) + wd for ws, wd in zip(waterStartTime, waterDuration))

        min_water = min(s + d for s, d in zip(waterStartTime, waterDuration))
        water_first = min(max(min_water, ls) + ld for ls, ld in zip(landStartTime, landDuration))

        return min(land_first, water_first)