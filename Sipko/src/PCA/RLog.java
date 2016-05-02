package PCA;

import java.io.IOException;

import pca.MatrixStruct;

public class RLog 
{
	public static void main(String[] args) throws IOException 
	{
		String expressionFN = "";
		String writeFolder = "";
		boolean writeAll = true;	
		double rLog = 1000000;
		double add = 0.5;

		for(int a = 0; a < args.length; a++)
		{
			String arg = args[a].split("=")[0];
			String value = args[a].split("=")[1];
			switch (arg.toLowerCase()){
				case "filename":
					expressionFN =value;
					break;
				case "writefolder":
					writeFolder = value;
					break;
				case "writeall":
					writeAll = Boolean.parseBoolean(value);
					break;
				case "totalreadcount":
					rLog = Double.parseDouble(value);
					break;	
				case "add":
					add = Double.parseDouble(value);
					break;	
				default:
					checkArgs(args);
					System.out.println("Incorrect argument supplied; exiting");
					System.exit(1);
			}
		}
		
		MatrixStruct expressionStruct = new MatrixStruct(expressionFN);
		rLog(writeFolder, expressionStruct, rLog, writeAll);
	}

	public static void rLog(String writeFolder, MatrixStruct expressionStruct, double rLog, boolean writeAll) throws IOException 
	{
		String swapFN = writeFolder + "swapFile.txt";
		expressionStruct.write(swapFN);
		pca.PCA.log(" 6. Rlog without log");
		String correctedNotLogged =  writeFolder+ "SAMPLE_Rlogged.txt";
		expressionStruct.rLog(rLog, writeFolder, swapFN);
		if(writeAll)
			expressionStruct.write(correctedNotLogged);
	}
	static void checkArgs(String[] args) 
	{
		if(System.getProperty("user.dir").contains("C:\\Users\\Sipko\\git\\PCrotation\\Sipko"))
			return;
		System.out.println("Wrong arguments");
		System.exit(1);
	}
}