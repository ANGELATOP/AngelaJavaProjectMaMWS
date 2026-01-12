package aa.common.code;

import java.io.File;
import java.io.FilenameFilter;

public class InputFileFilter implements FilenameFilter
{
	public boolean accept(File dir, String name)
	{
		boolean accepted = false;

//		if (name.startsWith("step_3")) 
		{
			accepted = true;
		}

		return accepted;
	}


}
