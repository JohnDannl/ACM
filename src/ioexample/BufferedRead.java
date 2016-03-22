package ioexample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BufferedRead {
	public static void main(String[] args) throws IOException{
		// It is advised to wrap BufferedReaders around FileReaders and InputStreamReaders
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String line;
		while((line=br.readLine())!=null)
			System.out.println(line);
	}
}
