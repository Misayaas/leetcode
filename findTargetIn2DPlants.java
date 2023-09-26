public class findTargetIn2DPlants {
    public boolean findTargetIn2DPlants(int[][] plants, int target) {
        int i = plants.length - 1, j = 0;
        while(i >= 0 && j < plants[0].length){
            if(plants[i][j] > target) i--;
            else if(plants[i][j] < target) j++;
            else return true;
        }
        return false;
    }
}
