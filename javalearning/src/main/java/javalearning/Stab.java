package javalearning;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;

import javalearning.core.ILearning;
import javalearning.core.control.CompileManager;
import javalearning.gui.MainFrame;

public class Stab {
	
	public static void main(String[] args) {
		// ソース文字列を生成
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);

		pw.println("package sample.string;");
		pw.println("import javalearning.core.ILearning;");
		pw.println("public class Sample3 implements ILearning {");
		pw.println("public String getValue() {");
		pw.println("return \"実行時にファイルを使わずコンパイル！\";");
		pw.println("}");
		pw.println("}");

		pw.close();
		String src = sw.toString();
		System.out.print(src);

	//コンパイルを実行
		CompileManager manager = new CompileManager();
		Class<ILearning> c = manager.compile("sample.string.Sample3", src); //キャストが間違っていても、ここではエラーにならない

	//インスタンスを生成して呼び出す
		ILearning s = null;
		try {
			s = c.getDeclaredConstructor().newInstance();
		} catch (InstantiationException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}	//もしClassのキャストが間違っていたら、ここで例外が発生する
		System.out.println(s.getValue());
		
		new MainFrame().doProcess();
	}

}
