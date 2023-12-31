package javalearning.core.stream;

import java.io.IOException;
import java.io.PrintStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javalearning.core.ui.panel.OutputPanel;

public class LearningPrintStream extends PrintStream {
	
	private Logger LOGGER = LogManager.getLogger(LearningPrintStream.class);
	
	public LearningPrintStream(LearnigOutputStream output) {
		super(output);
	}
	
	@Override
	public void print(boolean b) {
        write(String.valueOf(b));
    }

	@Override
    public void print(char c) {
        write(String.valueOf(c));
    }

	@Override
    public void print(int i) {
        write(String.valueOf(i));
    }

	@Override
    public void print(long l) {
        write(String.valueOf(l));
    }

	@Override
    public void print(float f) {
        write(String.valueOf(f));
    }

	@Override
    public void print(double d) {
        write(String.valueOf(d));
    }

	@Override
    public void print(char s[]) {
        write(String.valueOf(s));
    }

	@Override
    public void print(String s) {
        write(String.valueOf(s));
    }

	@Override
    public void print(Object obj) {
        write(String.valueOf(obj));
    }
    
	@Override
    public void println() {
        newLine();
    }
    
	@Override
    public void println(boolean x) {
        if (getClass() == LearningPrintStream.class) {
            writeln(String.valueOf(x));
        } else {
            synchronized (this) {
                print(x);
                newLine();
            }
        }
    }

	@Override
    public void println(char x) {
        if (getClass() == LearningPrintStream.class) {
            writeln(String.valueOf(x));
        } else {
            synchronized (this) {
                print(x);
                newLine();
            }
        }
    }

	@Override
    public void println(int x) {
        if (getClass() == LearningPrintStream.class) {
            writeln(String.valueOf(x));
        } else {
            synchronized (this) {
                print(x);
                newLine();
            }
        }
    }

	@Override
    public void println(long x) {
        if (getClass() == LearningPrintStream.class) {
            writeln(String.valueOf(x));
        } else {
            synchronized (this) {
                print(x);
                newLine();
            }
        }
    }

	@Override
    public void println(float x) {
        if (getClass() == LearningPrintStream.class) {
            writeln(String.valueOf(x));
        } else {
            synchronized (this) {
                print(x);
                newLine();
            }
        }
    }

	@Override
    public void println(double x) {
        if (getClass() == LearningPrintStream.class) {
            writeln(String.valueOf(x));
        } else {
            synchronized (this) {
                print(x);
                newLine();
            }
        }
    }

	@Override
    public void println(char[] x) {
        if (getClass() == LearningPrintStream.class) {
            writeln(String.valueOf(x));
        } else {
            synchronized (this) {
                print(x);
                newLine();
            }
        }
    }

	@Override
    public void println(String x) {
        if (getClass() == LearningPrintStream.class) {
            writeln(String.valueOf(x));
        } else {
            synchronized (this) {
                print(x);
                newLine();
            }
        }
    }

	@Override
    public void println(Object x) {
        String s = String.valueOf(x);
        if (getClass() == LearningPrintStream.class) {
            writeln(String.valueOf(s));
        } else {
            synchronized (this) {
                print(s);
                newLine();
            }
        }
    }
	
    public void newLine() {
    	write("\n");
    }
    
    public String getPrintValue() {
		try {
			OutputPanel panel = getOutputStream().getPanel();
			return panel.getText();
		} catch (IOException e) {
			LOGGER.error(e);
		}
		return "";
    }
    
	public LearnigOutputStream getOutputStream() throws IOException {
		if (!(out instanceof LearnigOutputStream)) {
			throw new IOException();
		}
		return (LearnigOutputStream)out;
	}
    
	private void write(String s) {
		try {
			OutputPanel panel = getOutputStream().getPanel();
			panel.appendText(s);
		} catch (IOException e) {
			LOGGER.error(e);
		}
	}
	
	
	private void writeln(String s) {
		write(s);
		newLine();
	}
}
