package cjj.tiexi.shenyang.tools;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import junit.framework.Test;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.Test() ;
    }

    public  String  Test()
    {
        return "Hello";
    }

}
