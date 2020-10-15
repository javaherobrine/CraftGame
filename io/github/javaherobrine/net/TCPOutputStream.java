package io.github.javaherobrine.net;
import java.io.*;
import java.net.*;
public class TCPOutputStream extends FilterOutputStream{
    public DataProcessor dataproc;
    public TCPOutputStream(OutputStream in) {
        this(in,PlainDataProcessor.DEFAULT_PROCESSOR);
    }
    public TCPOutputStream(OutputStream in,DataProcessor proc) {
        super(in);
        dataproc=proc;
    }
    public TCPOutputStream(Socket soc) throws IOException{
        this(soc.getOutputStream());
    }
    public TCPOutputStream(Socket soc,DataProcessor proc) throws IOException {
        this(soc.getOutputStream(),proc);
    }
    public void writeData(byte[] bs) throws IOException {
        dataproc.write(this,bs);
    }
}