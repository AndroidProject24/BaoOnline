package com.toan_itc.data.utils;

import android.os.Environment;
import android.os.StatFs;

import java.io.File;

/**
 * Created by Toan.IT
 * Date: 05/09/2016
 * Email: huynhvantoan.itc@gmail.com
 */
@SuppressWarnings("deprecation")
public class SdCard {
	public static boolean checkSdCard()
	{
		return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
	}


	public static long getSDcardTotalSize()
	{
		long blockSizeLong, blockCountLong;

		if (checkSdCard())
		{
			File path = Environment.getExternalStorageDirectory();
			StatFs mStatFs = new StatFs(path.getPath());
			if(android.os.Build.VERSION.SDK_INT < 18)
			{
				blockSizeLong = mStatFs.getBlockSize();
				blockCountLong = mStatFs.getBlockCount();
			}else{
				blockSizeLong= mStatFs.getBlockSizeLong();
				blockCountLong= mStatFs.getBlockCountLong();
			}
			long totalSize = blockSizeLong * blockCountLong;
			return totalSize;
		} else
		{
			return 0;
		}
	}

	public static long getSDcardAvailableSize()
	{
		long blockSizeLong, availableBlocksLong;
		if (checkSdCard())
		{
			File path = Environment.getExternalStorageDirectory();
			StatFs mStatFs = new StatFs(path.getPath());
			if(android.os.Build.VERSION.SDK_INT < 18)
			{
				blockSizeLong = mStatFs.getBlockSize();
				availableBlocksLong = mStatFs.getAvailableBlocks();
			}else{
				blockSizeLong= mStatFs.getBlockSizeLong();
				availableBlocksLong= mStatFs.getAvailableBlocksLong();
			}
			long availabSize = blockSizeLong * availableBlocksLong;
			return availabSize;
		} else
		{
			return 0;
		}
	}


	public static long getPhoneTotalSize()
	{
		long blockSizeLong, blockCountLong;
		if (!checkSdCard())
		{
			File path = Environment.getDataDirectory();
			StatFs mStatFs = new StatFs(path.getPath());
			if(android.os.Build.VERSION.SDK_INT < 18)
			{
				blockSizeLong = mStatFs.getBlockSize();
				blockCountLong = mStatFs.getBlockCount();
			}else{
				blockSizeLong= mStatFs.getBlockSizeLong();
				blockCountLong= mStatFs.getBlockCountLong();
			}
			long totalSize = blockSizeLong * blockCountLong;
			return totalSize;
		} else
		{
			return getSDcardTotalSize();
		}
	}


	public static long getPhoneAvailableSize()
	{
		long blockSizeLong, availableBlocksLong;
		if (!checkSdCard())
		{
			File path = Environment.getDataDirectory();
			StatFs mStatFs = new StatFs(path.getPath());
			if(android.os.Build.VERSION.SDK_INT < 18)
			{
				blockSizeLong = mStatFs.getBlockSize();
				availableBlocksLong = mStatFs.getAvailableBlocks();
			}else{
				blockSizeLong= mStatFs.getBlockSizeLong();
				availableBlocksLong= mStatFs.getAvailableBlocksLong();
			}
			long availabSize = blockSizeLong * availableBlocksLong;
			return availabSize;
		} else
		{
			return getSDcardAvailableSize();
		}
	}
}
