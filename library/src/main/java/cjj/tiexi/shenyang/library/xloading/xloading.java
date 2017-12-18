package cjj.tiexi.shenyang.library.xloading;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cjj.tiexi.shenyang.library.R;

/**
 * Created by CJJ on 2017/12/18.
 */

public class xloading
{

    /**
     * 显示Dialog
     * 具体用法
     *
     * public class MainActivity extends AppCompatActivity {
     private Dialog mDialog;
     private Dialog mWeiboDialog;
     private Button btn_show_weibo_loading;
     private Button btn_show_thrid_loading;
     private Handler mHandler = new Handler() {
    @Override
    public void handleMessage(Message msg) {
    super.handleMessage(msg);
    switch (msg.what) {
    case 1:
    DialogThridUtils.closeDialog(mDialog);
    WeiboDialogUtils.closeDialog(mWeiboDialog);
    break;
    }
    }
    };

     @Override
     protected void onCreate(Bundle savedInstanceState) {
     super.onCreate(savedInstanceState);
     setContentView(R.layout.activity_main);
     btn_show_weibo_loading = (Button) findViewById(R.id.btn_show_weibo_loading);
     btn_show_thrid_loading = (Button) findViewById(R.id.btn_show_thrid_loading);
     btn_show_weibo_loading.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View v) {
     mWeiboDialog = WeiboDialogUtils.createLoadingDialog(MainActivity.this, "加载中...");
     mHandler.sendEmptyMessageDelayed(1, 2000);
     }
     });

     btn_show_thrid_loading.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View v) {
     mDialog = DialogThridUtils.showWaitDialog(MainActivity.this, "加载中...", false, true);
     mHandler.sendEmptyMessageDelayed(1, 2000);
     }
     });
     }
     *
     * @param context  上下文
     * @param msg  显示内容
     * @param isTransBg 是否透明
     * @param isCancelable 是否可以点击取消
     * @return
     */
    public static Dialog showWaitDialog(Context context, String msg, boolean isTransBg, boolean isCancelable)
    {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.thridlogin_dialog_loading, null);             // 得到加载view
        RelativeLayout layout = (RelativeLayout) v.findViewById(R.id.dialog_view);// 加载布局

        // main.xml中的ImageView
        ImageView spaceshipImage = (ImageView) v.findViewById(R.id.img);
        TextView tipTextView = (TextView) v.findViewById(R.id.tipTextView);   // 提示文字
        // 加载动画
        Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(context, R.anim.rotate_animation);
        // 使用ImageView显示动画
        spaceshipImage.startAnimation(hyperspaceJumpAnimation);
        tipTextView.setText(msg);// 设置加载信息

        Dialog loadingDialog = new Dialog(context, isTransBg ? R.style.TransDialogStyle : R.style.WhiteDialogStyle);    // 创建自定义样式dialog
        loadingDialog.setContentView(layout);
        loadingDialog.setCancelable(isCancelable);
        loadingDialog.setCanceledOnTouchOutside(false);

        Window window = loadingDialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setGravity(Gravity.CENTER);
        window.setAttributes(lp);
        window.setWindowAnimations(R.style.PopWindowAnimStyle);
        loadingDialog.show();
        return loadingDialog;
    }

    /**
     * 关闭dialog
     *
     * http://blog.csdn.net/qq_21376985
     *
     * @param mDialogUtils
     */
    public static void closeDialog(Dialog mDialogUtils)
    {
        if (mDialogUtils != null && mDialogUtils.isShowing())
        {
            mDialogUtils.dismiss();
        }
    }

}
