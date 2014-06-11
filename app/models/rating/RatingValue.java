package models.rating;

/**
 * Based on five value LIKERT Scale
 * 
 * Excellent, Good, Satisfactory, Poor, Very poor
 * 
 * @author Chrysa Papapdaki - papadaki.chr@gmail.com
 * 
 */
public enum RatingValue {
    EXCELLENT(5), GOOD(4), SATISFACTORY(3), POOR(2), VERY_POOR(1);

    private int index;

    private RatingValue(int index) {
        this.index = index;
    }

    /**
     * @return rating value
     */
    public int getIndex() {
        return this.index;
    }
}
