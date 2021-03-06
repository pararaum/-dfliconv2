package dfliconv2.dithering;

import dfliconv2.Global;
import dfliconv2.Palette;

public class NoDithering extends Base 
{
	@Override
	public int select(int x, int y, float c0, float c1, float c2, int... colors) 
	{
		int bestColorIndex = -1;
		double bestError = Double.MAX_VALUE;
		for (int i = 0; i<colors.length; i++)
		{
			if (colors[i]<0)
				continue;
			double error = Palette.getColor(colors[i]).d2(c0,c1,c2);
			if (error<bestError-Global.eps)
			{
				bestError = error;
				bestColorIndex = i;
			}
		}
		if (bestColorIndex<0)
			throw new RuntimeException("Can't use any colors.");
		return bestColorIndex;
	}
}
