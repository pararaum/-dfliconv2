package dfliconv2.mode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import dfliconv2.Utils;
import dfliconv2.Value;
import dfliconv2.value.Const;

public class HiresBitmap extends HiresBitmapPlus 
{
	public HiresBitmap()
	{
		this(40,25);
	}
	
	public HiresBitmap(int w, int h) 
	{
		super(w,h,false);
	}
	
	public List<String> formats()
	{
		if (w==40 && h==25)
			return Arrays.asList("prg","boti","bin");
		else
			return Arrays.asList("bin");
	}
	
	public Map<String,List<Value>> files(String format) 
	{
		Map<String,List<Value>> fs = new LinkedHashMap<>();
		if (format.equals("bin"))
		{
			return super.files("bin");
		}
		else if (format.equals("prg"))
		{
			List<Value> prg = Utils.loadViewer("hires.prg");
			prg.addAll(luma);
			prg.addAll(Collections.nCopies(24, new Const(0)));
			prg.addAll(chroma);		
			prg.addAll(Collections.nCopies(24, new Const(0)));
			prg.addAll(bitmap);
			fs.put(".prg", prg);
		}
		else if (format.equals("boti"))
		{
			List<Value> file = new ArrayList<>();
			file.add(new Const(0x00));
			file.add(new Const(0x78));
			file.addAll(luma);
			file.addAll(Collections.nCopies(24, new Const(0)));
			file.addAll(chroma);
			file.addAll(Collections.nCopies(24, new Const(0)));
			file.addAll(bitmap);
			fs.put("_boti.prg",file);
		}
		return fs;
	}
}
