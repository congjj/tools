package cjj.tiexi.shenyang.library.messageutility;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * Created by CJJ on 2018/1/4.
 */

public class MessageDialog
{
    public interface DialogButtonPressInterface
    {
        public void ButtonPress_After(DialogResult.Result buttonResult);
    }

    public DialogButtonPressInterface SetOnAfterPressButtonListener;

    private DialogResult.Result mResult ;
    private Context mContext ;
    public MessageDialog (Context context )
    {
        mContext =context ;
    }

    public void  Show(String title, String message)
    {
        new AlertDialog.Builder(mContext )
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("是", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int which)
                    {
                        if(SetOnAfterPressButtonListener !=null)
                        {
                            SetOnAfterPressButtonListener .ButtonPress_After(DialogResult.Result .YES) ;
                        }
                    }
                })
                .show();
    }


    public void Show(String title, String message, DialogResult.DialogButton dialogButton)
    {
        if(dialogButton == DialogResult.DialogButton .Yes)
        {
            new AlertDialog.Builder(mContext )
                    .setTitle(title)
                    .setMessage(message)
                    .setPositiveButton("是", new DialogInterface.OnClickListener()
                    {
                        public void onClick(DialogInterface dialog, int which)
                        {
                            if(SetOnAfterPressButtonListener !=null)
                            {
                                SetOnAfterPressButtonListener .ButtonPress_After(DialogResult.Result .YES) ;
                            }
                        }
                    })
                    .show();
        }
        else if(dialogButton == DialogResult.DialogButton .YESNO )
        {
            new AlertDialog.Builder(mContext)
                    .setTitle(title)
                    .setMessage(message)
                    .setPositiveButton("是", new DialogInterface.OnClickListener()
                    {
                        public void onClick(DialogInterface dialog, int which)
                        {
                            if(SetOnAfterPressButtonListener !=null)
                            {
                                SetOnAfterPressButtonListener .ButtonPress_After(DialogResult.Result .YES) ;
                            }
                        }
                    })
                    .setNegativeButton("否", new DialogInterface.OnClickListener()
                    {
                        public void onClick(DialogInterface dialog, int which)
                        {
                            if(SetOnAfterPressButtonListener !=null)
                            {
                                SetOnAfterPressButtonListener .ButtonPress_After(DialogResult.Result .NO) ;
                            }
                        }
                    })
                    .show();
        }
    }


}
