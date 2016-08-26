package PCA_testcases;

import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import PCA.MatrixStruct;
import PCA.RLog;
import PCA.RlogLargeMatrix;

public class RlogLargeMatrix_testcase {

	@Rule
	public TemporaryFolder folder = new TemporaryFolder();
	@Test
	public void test() throws IOException 
	{

		System.out.println("Test folder: " + folder.getRoot());
		
		String fileName = "TestCaseFiles/Samples.txt";
		String writeFolder = folder.getRoot().getAbsolutePath()+"/";
		String[] args = new String[]{"filename="+fileName,"writeFolder="+writeFolder};
		System.out.println("Test folder: " + writeFolder);
		RlogLargeMatrix.main(args);
		
		String rlogFN= writeFolder+"Samples.DESeqNorm.txt.gz";
		String geoFN= writeFolder+"/geoMean.txt";
		
		CompareFiles.compare(geoFN,"TestCaseFiles/Result_Geomean.txt",true);
		String denominatorsFN= writeFolder+"Denominators.txt";	
		CompareFiles.compare(denominatorsFN,"TestCaseFiles/Result_Denominators.txt",true);
		CompareFiles.compare(rlogFN,"TestCaseFiles/Result_Samples.txt",true);
	}

}