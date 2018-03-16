import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArrayCropperTest {

    @Test
    public void getCroppedFieldAsArrayTest() {
        int[][] src =
                        {{0, 0, 0, 0},
                        {0, 1, 0, 0},
                        {0, 0, 1, 0},
                        {1, 1, 1, 0},
                        {0, 0, 0, 0}};

        int[][] cropped =
                        {{0, 1, 0},
                        {0, 0, 1},
                        {1, 1, 1}};

        int[][] res = ArrayCropper.getCroppedFieldAsArray(src);

        assertEquals(cropped.length, res.length);

        for (int i = 0; i < res.length; i++) {
            assertArrayEquals(cropped[i], res[i]);
        }
    }
}

