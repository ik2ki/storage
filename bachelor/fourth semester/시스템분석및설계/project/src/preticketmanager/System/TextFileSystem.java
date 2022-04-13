package preticketmanager.System;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TextFileSystem {
	File file;
	String route;
	BufferedReader fileReader;
	BufferedWriter fileWriter;
	
	public TextFileSystem(){
		file = null;
		route = "./src/preticketmanager/data/";
		fileReader = null;
		fileWriter = null;
	}
	public void createFile(String fileName) throws IOException{
		file = new File(route + fileName);
		file.createNewFile();
	}
	public void setFile(String fileName){
		file = new File(route + fileName);
		if (!file.exists())
			System.err.println(route + "에 " +"'" + fileName + "' 파일이 존재하지 않습니다.");
	}
	public void initRoute(){
		route = "./src/preticketmanager/data/";
	}
	public void addRoute(String route){
		this.route += route + "/";
	}
	public boolean setReader() throws FileNotFoundException{
		if(file.canRead()){
			fileReader = new BufferedReader(new FileReader(file));
			return true;
		}
		else{
			System.err.println("'" + file.getName() + "' 읽을 수 없는 파일입니다.");
			return false;
		}
	}
	public boolean setWriter() throws IOException{
		if(file.canWrite()){
			fileWriter = new BufferedWriter(new FileWriter(file));
			return true;
		}
		else{
			System.err.println("'" + file.getName() + "' 쓸 수 없는 파일입니다.");
			return false;
		}
	}
	public String readLine(int numOfCharacter) throws IOException{
		int buffer;
		String str = "";
		
		for(int i = 0; (buffer = fileReader.read()) != -1 && (char)buffer != '\n' && i < 2 * numOfCharacter; i++){
			str += (char)buffer;
		}
		if(str == "")
			return str;
		else
			return str + "\n";
	}
	public String read(int numOfCharacter, int rows) throws IOException{
		String str = "";
		String temp = null;
		for(int i = 0; ((temp = readLine(numOfCharacter)) != "" && i < rows); i++){
			str += temp;
		}
		return str;
	}
	public String readAll() throws IOException{
		String str = "";
		int buffer;
		while((buffer = fileReader.read()) != -1){
			str += (char)buffer;
		}
		return str;
	}
	public String readAll(int numOfCharacter) throws IOException{
		String str = "";
		String temp = null;
		while((temp = readLine(numOfCharacter)) != ""){
			str += temp;
		}
			
		return str;
	}
	public String readProcessDot(int numOfCharacter) throws IOException{
		int buffer;
		String str = "";
		
		for(int i = 0; (buffer = fileReader.read()) != -1 && i < 2 * numOfCharacter; i++){
			str += (char)buffer;
		}
		if(str == "")
			return str;
		else
			return str + "...";
	}
	public void write(String str) throws IOException{
		if (!file.exists())
			file.createNewFile();
		fileWriter.write(str);
	}
	public void close() throws IOException{
		if(fileReader == null)
			fileReader.close();
		if(fileWriter == null)
			fileWriter.close();
	}
}