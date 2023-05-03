package com.reydevz.almz;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.os.StrictMode;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.reydevz.almz.model.UpdateAsync;
import android.annotation.SuppressLint;
import android.content.pm.PackageInfo;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.reydevz.almz.Graph.DataTransferGraph2;
import com.reydevz.almz.Graph.GraphHelper;
import com.reydevz.almz.Graph.StoredData;
import com.reydevz.almz.activities.AboutActivity;
import com.reydevz.almz.activities.BaseActivity;
import com.reydevz.almz.activities.ConfigGeralActivity;
import com.reydevz.almz.adapter.LogsAdapter;
import com.reydevz.almz.adapter.PromoAdapter;
import com.reydevz.almz.adapter.SetupAdapter;
import com.reydevz.almz.adapter.SpinnerAdapter;
import com.reydevz.almz.error.e;
import com.reydevz.almz.sshservice.LaunchVpn;
import com.reydevz.almz.sshservice.SocksHttpService;
import com.reydevz.almz.sshservice.config.ConfigParser;
import com.reydevz.almz.sshservice.config.Settings;
import com.reydevz.almz.sshservice.logger.ConnectionStatus;
import com.reydevz.almz.sshservice.logger.SkStatus;
import com.reydevz.almz.sshservice.tunnel.TunnelManagerHelper;
import com.reydevz.almz.sshservice.tunnel.TunnelUtils;
import com.reydevz.almz.sshservice.util.SkProtect;
import com.reydevz.almz.sshservice.util.securepreferences.SecurePreferences;
import com.reydevz.almz.util.AESCrypt;
import com.reydevz.almz.util.ConfigUpdate;
import com.reydevz.almz.util.ConfigUtil;
import com.reydevz.almz.util.RetrieveData;
import com.reydevz.almz.util.Utils;
import com.github.mikephil.charting.charts.*;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.install.InstallStateUpdatedListener;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.install.model.InstallStatus;
import com.google.android.play.core.install.model.UpdateAvailability;
import com.google.firebase.FirebaseApp;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import com.reydevz.almz.util.Stored;
import android.util.TypedValue;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import androidx.appcompat.app.AppCompatDelegate;
import com.reydevz.almz.sshservice.config.Settings;
import com.reydevz.almz.wifi.MainActivityWifi;
import com.reydevz.almz.iphunt;
import android.widget.CompoundButton;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.app.AlarmManager;
import android.graphics.Color;
import java.net.URLConnection;
import java.net.URL;
import java.net.HttpURLConnection;
import java.util.Map;
import java.util.HashMap;
import java.net.URLEncoder;
import java.util.StringJoiner;
import java.util.Random;
import java.nio.charset.StandardCharsets;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.AdError;
import android.widget.ProgressBar;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.AdListener;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;


public class MainActivity extends BaseActivity
        implements View.OnClickListener,
                RadioGroup.OnCheckedChangeListener,
                CompoundButton.OnCheckedChangeListener,
                NavigationView.OnNavigationItemSelectedListener,
                AdapterView.OnItemSelectedListener,
                SkStatus.StateListener {

    private long mStartTimeInMillis;

    public RewardedAd rewardedAd;

    private AlertDialog alert1;

    public InterstitialAd interstitialAd;

    private AdView adsBannerView;

    @Override
    public void onCheckedChanged(CompoundButton p1, boolean p2) {}

    public static final String TODAY_DATA = "todaydata";
    private static final String[] tabTitle = {"HOME", "LOGS", "STATS"};
    private static final String UPDATE_VIEWS = "MainUpdate";
    private static final int RC_APP_UPDATE = 100;
    private static final int S_ONSTART_CALLED = 2;
    private static final int S_BIND_CALLED = 1;
    private static final long COUNTER_TIME = 10;
    public static SharedPreferences sp;
    public static SharedPreferences coins;
    public static SharedPreferences sp1;
    public static boolean mConnected;
    public ConfigUtil config;
    public Settings mConfig;
    public TextView bytes_in_view;
    public TextView bytes_out_view;
    NavigationView navi;
    private Toolbar toolbar_main;
    Button starterButton;
    boolean mTimerEnabled;
    long mEndTime;
    boolean cancelProgressBar;
    LogsAdapter mLogAdapter;
    RecyclerView logList;
    ViewPager vp;
    TabLayout tabs;
    SwitchMaterial imgFavorite;
    String[] setupList = {
        "Direct Connection", "Custom Payload", "Custom SNI", "CustomSNI + Payload",
    };
    int status = 0;
    private TextView connectionStatus;
    private DrawerLayout drawer;
    private Handler mHandler;
    private TextView mTextViewCountDown;
    private long mTimeLeftInMillis;
    private boolean mTimerRunning;
    private long saved_ads_time;
    private CountDownTimer mCountDownTimer;
    private TextView configversion1;
    private AppUpdateManager mAppUpdateManager;
    private final InstallStateUpdatedListener installUpdatelistener =
            state -> {
                if (state.installStatus() == InstallStatus.DOWNLOADED) {
                    showCompleterUpdate();
                }
            };
    boolean isLoading;
    private Button mButtonSet;
    private SpinnerAdapter serverAdapter;
    private PromoAdapter payloadAdapter;
    private Spinner serverSpinner;
    private Spinner payloadSpinner;
    private ArrayList<JSONObject> serverList;
    private ArrayList<JSONObject> payloadList;
    private SharedPreferences myData;
    private TextInputLayout sslPayloadLay;
    private TextInputLayout payLaySsl;
    private TextInputLayout edUserLayout;
    private TextInputLayout edPassLayout;
    private EditText webuser;
    private EditText webpass;
    private TextInputLayout bugsLayout;
    private EditText bugremote;
    private EditText edPayload;
    private EditText edSsl;
    private EditText sslPayload;
    private EditText edSslpayload;
    private TextInputLayout payLay, sslLay;
    private Spinner setupSpinner;
    private LinearLayout messLay;
    private SharedPreferences prefs;
    private SwitchCompat customPayloadSwitch;
    private TextInputEditText payloadEdit;
    private LinearLayout proxyInputLayout;
    private LinearLayout payloadLayout;
    private TextView proxyText;
    private RadioGroup metodoConexaoRadio;
    private LinearLayout loginLayout;
    private LinearLayout configMsgLayout;
    private TextView configMsgText;

    private Thread dataThread;
    private Thread dataUpdate;
    private Handler fHandler = new Handler();
    private GraphHelper graph;
    private LineChart mChart;

    private final BroadcastReceiver mActivityReceiver =
            new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    String action = intent.getAction();
                    if (action == null) return;

                    if (action.equals(UPDATE_VIEWS) && !isFinishing()) {
                        doUpdateLayout();
                    }
                }
            };
    private TextView tvMess;
    private SecurePreferences.Editor edit;
    private SecurePreferences.Editor putInt;
    private int PICK_FILE;
    private RelativeLayout htoRelativeLayout1;
    private TextView txt1;
    private TextView txt2;
    private AdView mAdView;

    private void loadbannerads() {
        mAdView = findViewById(R.id.adBannerfirstView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mAdView.setAdListener(
                new AdListener() {
                    @Override
                    public void onAdLoaded() {}

                    @Override
                    public void onAdFailedToLoad(LoadAdError adError) {}

                    @Override
                    public void onAdOpened() {}

                    @Override
                    public void onAdClicked() {
                        mAdView.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAdClosed() {}
                });
    }

    @Override
    public void onItemSelected(AdapterView<?> p1, View p2, int p3, long p4) {}

    @Override
    public void onNothingSelected(AdapterView<?> p1) {}

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sp = PreferenceManager.getDefaultSharedPreferences(this);
        mHandler = new Handler();
        mConfig = new Settings(this);
        config = new ConfigUtil(this);
        AutoupdateApp();
        loadAd();
        onShowe();
        SharedPreferences prefs = mConfig.getPrefsPrivate();
        myData = getSharedPreferences(TODAY_DATA, Context.MODE_PRIVATE);
        SkProtect.CharlieProtect();
        sp = new Settings(this).getPrefsPrivate();
        mHandler = new Handler(Looper.getMainLooper());
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        mAppUpdateManager = AppUpdateManagerFactory.create(this);
        mAppUpdateManager
                .getAppUpdateInfo()
                .addOnSuccessListener(
                        result -> {
                            if (result.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                                    && result.isUpdateTypeAllowed(AppUpdateType.FLEXIBLE)) {
                                try {
                                    mAppUpdateManager.startUpdateFlowForResult(
                                            result,
                                            AppUpdateType.IMMEDIATE,
                                            MainActivity.this,
                                            RC_APP_UPDATE);
                                } catch (IntentSender.SendIntentException ignored) {
                                }
                            }
                        });

        mAppUpdateManager.registerListener(installUpdatelistener);
        FirebaseApp.initializeApp(this);

        if (dontme.isDeviceRooted()) {
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }

        sp = new Settings(this).getPrefsPrivate();
        boolean showFirstTime = prefs.getBoolean("connect_first_time", true);
        int lastVersion = prefs.getInt("last_version", 0);

        if (showFirstTime) {
            SharedPreferences.Editor pEdit = prefs.edit();
            pEdit.putBoolean("connect_first_time", false);
            pEdit.apply();
            Settings.setDefaultConfig(this);
        }

        try {
            int idAtual = ConfigParser.getBuildId(this);

            if (lastVersion < idAtual) {
                SharedPreferences.Editor pEdit = prefs.edit();
                pEdit.putInt("last_version", idAtual);
                pEdit.apply();

                if (!showFirstTime) {
                    if (lastVersion <= 12) {
                        Settings.setDefaultConfig(this);
                        Settings.clearSettings(this);
                    }
                }
            }
        } catch (IOException ignored) {
        }
        IntentFilter filter = new IntentFilter();
        filter.addAction(UPDATE_VIEWS);

        LocalBroadcastManager.getInstance(this).registerReceiver(mActivityReceiver, filter);

        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));
        sp1 = PreferenceManager.getDefaultSharedPreferences(this);
        doLayout();
        loadServer();
        loadNetworks();
        doUpdateLayout();
        doTabs();
        setSpinner();
        loadbannerads();
        if (!Stored.isSetData) {
            Stored.setZero();
        }
        if (!Stored.isSetData) {
            Stored.setZero();
        }
        liveData();
        liveDataS();
    }

    private void darkModes() {

        final boolean isNightMode = new Settings(this).getModoNoturno().equals("on");

        if (isNightMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            new Settings(this).setModoNoturno("off");
            recreate();
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            new Settings(this).setModoNoturno("on");
            recreate();
        }
    }

    public void offlineUpdate() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        startActivityForResult(intent, PICK_FILE);
    }

    private String getLog() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < mLogAdapter.getItemCount(); i++) {
            str.append(mLogAdapter.getItem(i) + "\n");
        }
        return str.toString();
    }

    void MDToast(String str) {
        Toast.makeText(this, str, 0).show();
    }

    private void logClear() {
        mLogAdapter.clearLog();
        // Toast.makeText(MainActivity.this, "Log Clear", Toast.LENGTH_SHORT).show();
        // SkStatus.logInfo("<font color='red'>Log Cleared!</font>");
        customToast("Log Cleared Successfully", "");
    }

    public void doTabs() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mLogAdapter = new LogsAdapter(layoutManager, this);
        logList = (RecyclerView) findViewById(R.id.recyclerLog);
        logList.setAdapter(mLogAdapter);
        logList.setLayoutManager(layoutManager);
        mLogAdapter.scrollToLastPosition();
        vp = (ViewPager) findViewById(R.id.viewpager);
        tabs = (TabLayout) findViewById(R.id.tablayout);
        vp.setAdapter(new MyAdapter(Arrays.asList(tabTitle)));
        vp.setOffscreenPageLimit(3);
        tabs.setTabMode(TabLayout.MODE_FIXED);
        tabs.setTabGravity(TabLayout.GRAVITY_FILL);
        tabs.setupWithViewPager(vp);
        vp.setOnPageChangeListener(
                new ViewPager.SimpleOnPageChangeListener() {
                    @Override
                    public void onPageSelected(int position) {
                        if (position == 0) {
                            toolbar_main.getMenu().clear();
                            getMenuInflater().inflate(R.menu.main_menu, toolbar_main.getMenu());
                        } else if (position == 1) {
                            toolbar_main.getMenu().clear();
                            getMenuInflater().inflate(R.menu.maun_menu, toolbar_main.getMenu());
                        } else if (position == 2) {
                            toolbar_main.getMenu().clear();
                            getMenuInflater().inflate(R.menu.main_menu, toolbar_main.getMenu());
                        }
                    }
                });
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        showInterstitial();
        switch (item.getItemId()) {
            case R.id.updateresources:
                updateConfig(false);
                break;

            case R.id.miAbout:
                Intent aboutIntent = new Intent(this, AboutActivity.class);
                startActivity(aboutIntent);
                break;

            case R.id.wifi:
                Intent wifi = new Intent(this, MainActivityWifi.class);
                startActivity(wifi);
                break;

            case R.id.iphunts:
                Intent iphunt = new Intent(this, iphunt.class);
                startActivity(iphunt);
                break;

            case R.id.imao:
                imao();
                break;

            case R.id.vv1:
                Intent coins = new Intent(this, CoinsActivity.class);
                startActivity(coins);
                break;

            case R.id.fb:
                String url1 = "https://www.facebook.com/OfficialRSPHVPN";
                Intent intent2 = new Intent(Intent.ACTION_VIEW, Uri.parse(url1));
                intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(Intent.createChooser(intent2, getText(R.string.open_with)));
                break;

            case R.id.devz:
                String url3 = "https://m.me/sindayen.reymar.7";
                Intent intent3 = new Intent(Intent.ACTION_VIEW, Uri.parse(url3));
                intent3.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(Intent.createChooser(intent3, getText(R.string.open_with)));
                break;

            case R.id.miShareApp:
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(
                        Intent.EXTRA_TEXT,
                        "Download "
                                + getString(R.string.app_name)
                                + " App Thank you for sharing this app :\n\n https://play.google.com/store/apps/details?id=com.reydevz.almz");
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
                startActivity(Intent.createChooser(sharingIntent, "Share RSPH VPN App"));
                break;

        }
        drawer.closeDrawer(Gravity.START);
        return true;
    }

    public void AutoupdateApp() {
        new UpdateAsync(
                        MainActivity.this,
                        new UpdateAsync.Listener() {
                            @Override
                            public void onCompleted(final String config) {
                                SharedPreferences mPref =
                                        PreferenceManager.getDefaultSharedPreferences(
                                                MainActivity.this);
                                try {
                                    JSONObject obj = new JSONObject(config);
                                    if (obj.getString("newVersion")
                                            .equals(getAppInfoString(MainActivity.this))) {

                                    } else {
                                        try {
                                            mPref.edit()
                                                    .putString(
                                                            "version_Notes",
                                                            obj.getString("versionNotes"))
                                                    .apply();
                                            AppInfo(
                                                    "Update your app!",
                                                    obj.getString("versionNotes"),
                                                    obj.getString("apkUrl"),
                                                    obj.getString("newVersion"));
                                            customToast(
                                                    " New Version Detected, Please Install. ", "");
                                        } catch (org.json.JSONException e) {
                                            Toast.makeText(
                                                            MainActivity.this,
                                                            e.getMessage(),
                                                            Toast.LENGTH_SHORT)
                                                    .show();
                                        }
                                    }
                                } catch (Exception e) {
                                    Toast.makeText(
                                                    MainActivity.this,
                                                    e.getMessage(),
                                                    Toast.LENGTH_SHORT)
                                            .show();
                                }
                            }

                            @Override
                            public void onCancelled() {
                                // TODO: Implement this method
                            }

                            @Override
                            public void onException(String ex) {}
                        })
                .execute();
    }

    private String getAppInfoString(Context c) {
        c.getPackageManager();
        String version = "";
        try {
            @SuppressLint("PackageManagerGetSignatures")
            PackageInfo packageinfo = c.getPackageManager().getPackageInfo(c.getPackageName(), 0);
            version = String.format(packageinfo.versionName);

        } catch (PackageManager.NameNotFoundException e) {
        }
        return version;
    }

    private void AppInfo(String s1, final String s2, final String s3, final String s4) {
        LayoutInflater inflater =
                (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View inflate = inflater.inflate(R.layout.ru, (ViewGroup) null);
        AlertDialog.Builder builer = new AlertDialog.Builder(this);
        builer.setView(inflate);
        TextView app = (TextView) inflate.findViewById(R.id.happTextView1);
        TextView txtAppLogs = (TextView) inflate.findViewById(R.id.happTextView2);
        TextView version = (TextView) inflate.findViewById(R.id.happTextView3);
        LinearLayout Layout = (LinearLayout) inflate.findViewById(R.id.happLinearLayout1);
        Button update = (Button) inflate.findViewById(R.id.appButton1);
        app.setText(s1);
        txtAppLogs.setText(s2);
        version.setText(
                "Current Version: " + getAppInfoString(this) + "\n" + "Latest Version: " + s4);
        if (s1.equals("No updates available")) {
            Layout.setVisibility(View.GONE);
            update.setVisibility(View.GONE);
        } else {
            Layout.setVisibility(View.VISIBLE);
            update.setVisibility(View.VISIBLE);
        }
        final AlertDialog alert = builer.create();
        alert.setCancelable(false);
        alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alert.getWindow().setGravity(Gravity.CENTER);
        alert.show();
        update.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alert.dismiss();
                        Intent intent3 = new Intent(Intent.ACTION_VIEW, Uri.parse(s3));
                        intent3.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(
                                Intent.createChooser(
                                        intent3, MainActivity.this.getText(R.string.open_with)));
                    }
                });
    }

    @Override
    protected void onStop() {
        if (mAppUpdateManager != null) mAppUpdateManager.unregisterListener(installUpdatelistener);
        super.onStop();
    }

    private void showCompleterUpdate() {
        Snackbar snacks =
                Snackbar.make(
                        findViewById(android.R.id.content),
                        "New app is ready!",
                        Snackbar.LENGTH_INDEFINITE);
        snacks.setAction("Install", view -> mAppUpdateManager.completeUpdate());
        snacks.setActionTextColor(Color.parseColor("#ffffff"));
        snacks.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RC_APP_UPDATE && resultCode != RESULT_OK) {
            customToast(" Update Cancelled ", "");
        }
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_FILE) {
            if (resultCode == RESULT_OK) {
                try {
                    Uri uri = data.getData();
                    String intentData = importer(uri);
                    File file = new File(getFilesDir(), "Config.json");
                    OutputStream out = new FileOutputStream(file);
                    out.write(intentData.getBytes());
                    out.flush();
                    out.close();
                    loadServer();
                    loadNetworks();
                    //   Utils.restart_app(MainActivity.this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String importer(Uri uri) {
        BufferedReader reader = null;
        StringBuilder builder = new StringBuilder();
        try {
            reader =
                    new BufferedReader(
                            new InputStreamReader(getContentResolver().openInputStream(uri)));

            String line = "";
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    @Override
    public void onCheckedChanged(RadioGroup p1, int p2) {
        SharedPreferences.Editor edit = mConfig.getPrefsPrivate().edit();

        switch (p1.getCheckedRadioButtonId()) {
            case R.id.activity_mainSSHDirectRadioButton:
                edit.putInt(Settings.TUNNELTYPE_KEY, Settings.bTUNNEL_TYPE_SSH_DIRECT);
                proxyInputLayout.setVisibility(View.GONE);
                break;

            case R.id.activity_mainSSHProxyRadioButton:
                edit.putInt(Settings.TUNNELTYPE_KEY, Settings.bTUNNEL_TYPE_SSH_PROXY);
                proxyInputLayout.setVisibility(View.VISIBLE);
                break;
        }

        edit.apply();
        dosavedata();
        doUpdateLayout();
    }

    private synchronized void dosavedata() {
        try {
            SharedPreferences prefs = mConfig.getPrefsPrivate();
            SharedPreferences.Editor edit = prefs.edit();

            if (imgFavorite.isChecked()) {
                int pos = setupSpinner.getSelectedItemPosition();
                int pos1 = serverSpinner.getSelectedItemPosition();
                switch (pos) {
                    case 0:
                        prefs.edit().putBoolean(Settings.PROXY_USAR_DEFAULT_PAYLOAD, false).apply();
                        prefs.edit()
                                .putInt(Settings.TUNNELTYPE_KEY, Settings.bTUNNEL_TYPE_SSH_DIRECT)
                                .apply();
                        break;
                    case 1:
                        prefs.edit()
                                .putInt(Settings.TUNNELTYPE_KEY, Settings.bTUNNEL_TYPE_SSH_PROXY)
                                .apply();
                        String payload = edPayload.getText().toString();
                        edit.putString(Settings.CUSTOM_PAYLOAD_KEY, payload);
                        String ssh_port =
                                config.getServersArray()
                                        .getJSONObject(pos1)
                                        .getString("ServerPort");
                        edit.putString(Settings.SERVIDOR_PORTA_KEY, ssh_port);
                        prefs.edit().putString("SavedHTTP", payload).apply();
                        break;
                    case 2:
                        prefs.edit()
                                .putInt(
                                        Settings.TUNNELTYPE_KEY,
                                        Settings.bTUNNEL_TYPE_SSH_SSLTUNNEL)
                                .apply();
                        String sni = edSsl.getText().toString();
                        edit.putString(Settings.CUSTOM_PAYLOAD_KEY, sni);
                        String ssl_port =
                                config.getServersArray().getJSONObject(pos1).getString("SSLPort");
                        edit.putString(Settings.SERVIDOR_PORTA_KEY, ssl_port);
                        prefs.edit().putString("SavedSSL", sni).apply();
                        break;
                    case 3:
                        prefs.edit()
                                .putInt(Settings.TUNNELTYPE_KEY, Settings.bTUNNEL_TYPE_PAY_SSL)
                                .apply();
                        String sslpayload = sslPayload.getText().toString();
                        String snipayload = edSslpayload.getText().toString();
                        String user = webuser.getText().toString();
                        String pass = webpass.getText().toString();
                        String bughost1 = bugremote.getText().toString();
                        edit.putString(Settings.CUSTOM_SNI, snipayload);
                        edit.putString(Settings.CUSTOM_PAYLOAD_KEY, sslpayload);
                        edit.putString(Settings.USUARIO_KEY, user);
                        edit.putString(Settings.SENHA_KEY, pass);
                        edit.putString(Settings.SERVIDOR_KEY, bughost1);
                        String ssl_port2 =
                                config.getServersArray().getJSONObject(pos1).getString("SSLPort");
                        edit.putString(Settings.SERVIDOR_PORTA_KEY, ssl_port2);
                        prefs.edit().putString("SavedHTTP + SSL", sslpayload).apply();
                        prefs.edit().putString("SavedSSL + HTTP", snipayload).apply();
                        prefs.edit().putString("SavedUSER", user).apply();
                        prefs.edit().putString("SavedPASS", pass).apply();
                        prefs.edit().putString("SavedBUG", bughost1).apply();
                        break;
                }
            } else {
                if (!prefs.getBoolean(Settings.CONFIG_PROTEGER_KEY, false)) {
                    if (!prefs.getBoolean(Settings.PROXY_USAR_DEFAULT_PAYLOAD, true)) {
                        int pos = payloadSpinner.getSelectedItemPosition();
                        // int modeType =
                        // prefs.getInt("TunnelMode",modeGroup.getCheckedRadioButtonId());

                        boolean directModeType =
                                config.getNetworksArray().getJSONObject(pos).getBoolean("isSSL");
                        boolean sshssltype =
                                config.getNetworksArray()
                                        .getJSONObject(pos)
                                        .getBoolean("wsPayload");
                        boolean remotessltype =
                                config.getNetworksArray()
                                        .getJSONObject(pos)
                                        .getBoolean("remoteHost");
                        boolean slowdnstype =
                                config.getNetworksArray().getJSONObject(pos).getBoolean("SlowDns");

                        if (directModeType) {
                            prefs.edit()
                                    .putInt(
                                            Settings.TUNNELTYPE_KEY,
                                            Settings.bTUNNEL_TYPE_SSH_SSLTUNNEL)
                                    .apply();
                            String sni =
                                    config.getNetworksArray().getJSONObject(pos).getString("SNI");
                            edit.putString(Settings.CUSTOM_PAYLOAD_KEY, sni);

                        } else if (sshssltype) {
                            prefs.edit()
                                    .putInt(Settings.TUNNELTYPE_KEY, Settings.bTUNNEL_TYPE_PAY_SSL)
                                    .apply();
                            String payload =
                                    config.getNetworksArray()
                                            .getJSONObject(pos)
                                            .getString("Payload");
                            String snissl =
                                    config.getNetworksArray().getJSONObject(pos).getString("SNI");
                            edit.putString(Settings.CUSTOM_PAYLOAD_KEY, payload);
                            edit.putString(Settings.CUSTOM_SNI, snissl);

                        } else if (remotessltype) {
                            prefs.edit()
                                    .putInt(Settings.TUNNELTYPE_KEY, Settings.bTUNNEL_TYPE_SSL_RP)
                                    .apply();
                            String payloadrp =
                                    config.getNetworksArray()
                                            .getJSONObject(pos)
                                            .getString("Payload");
                            String sslrp =
                                    config.getNetworksArray().getJSONObject(pos).getString("SNI");
                            edit.putString(Settings.CUSTOM_PAYLOAD_KEY, payloadrp);
                            edit.putString(Settings.CUSTOM_SNI, sslrp);

                        } else if (slowdnstype) {
                            prefs.edit()
                                    .putInt(Settings.TUNNELTYPE_KEY, Settings.bTUNNEL_TYPE_SLOWDNS)
                                    .apply();
                            String chvKey =
                                    config.getNetworksArray()
                                            .getJSONObject(pos)
                                            .getString("chaveKey");
                            String nvKey =
                                    config.getNetworksArray()
                                            .getJSONObject(pos)
                                            .getString("serverNameKey");
                            String dnsKey =
                                    config.getNetworksArray()
                                            .getJSONObject(pos)
                                            .getString("dnsKey");

                            edit.putString(Settings.CHAVE_KEY, chvKey);
                            edit.putString(Settings.NAMESERVER_KEY, nvKey);
                            edit.putString(Settings.DNS_KEY, dnsKey);

                        } else {
                            prefs.edit()
                                    .putInt(
                                            Settings.TUNNELTYPE_KEY,
                                            Settings.bTUNNEL_TYPE_SSH_PROXY)
                                    .apply();
                            String payload =
                                    config.getNetworksArray()
                                            .getJSONObject(pos)
                                            .getString("Payload");
                            edit.putString(Settings.CUSTOM_PAYLOAD_KEY, payload);
                        }
                    }
                }
            }
            edit.apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadServerData() {
        try {
            SharedPreferences prefs = mConfig.getPrefsPrivate();
            SharedPreferences.Editor edit = prefs.edit();
            int pos1 = serverSpinner.getSelectedItemPosition();
            int pos2 = payloadSpinner.getSelectedItemPosition();

            boolean directModeType =
                    config.getNetworksArray().getJSONObject(pos2).getBoolean("isSSL");
            boolean sshssltype =
                    config.getNetworksArray().getJSONObject(pos2).getBoolean("wsPayload");
            boolean remotessltype =
                    config.getNetworksArray().getJSONObject(pos2).getBoolean("remoteHost");
            boolean slowdnstype =
                    config.getNetworksArray().getJSONObject(pos2).getBoolean("SlowDns");
            if (directModeType) {
                String ssl_port = config.getServersArray().getJSONObject(pos1).getString("SSLPort");
                edit.putString(Settings.SERVIDOR_PORTA_KEY, ssl_port);

            } else if (sshssltype) {
                String ssl_port1 =
                        config.getServersArray().getJSONObject(pos1).getString("SSLPort");
                edit.putString(Settings.SERVIDOR_PORTA_KEY, ssl_port1);

            } else if (remotessltype) {
                String ssl_port2 =
                        config.getServersArray().getJSONObject(pos1).getString("SSLPort");
                edit.putString(Settings.SERVIDOR_PORTA_KEY, ssl_port2);

            } else if (slowdnstype) {
                edit.putString(Settings.SERVIDOR_KEY, "127.0.0.1");
                edit.putString(Settings.SERVIDOR_PORTA_KEY, "2222");

            } else {
                String ssh_port =
                        config.getServersArray().getJSONObject(pos1).getString("ServerPort");
                edit.putString(Settings.SERVIDOR_PORTA_KEY, ssh_port);
            }

            String ssh_server = config.getServersArray().getJSONObject(pos1).getString("ServerIP");
            String remote_proxy = config.getServersArray().getJSONObject(pos1).getString("ProxyIP");
            String proxy_port = config.getServersArray().getJSONObject(pos1).getString("ProxyPort");
            String ssh_user = config.getServersArray().getJSONObject(pos1).getString("ServerUser");
            String ssh_pass = config.getServersArray().getJSONObject(pos1).getString("ServerPass");
            String u = config.getServersArray().getJSONObject(pos1).getString("Name");
            String o = config.getNetworksArray().getJSONObject(pos2).getString("Name");

            try {

                edit.putString(
                        Settings.USUARIO_KEY, AESCrypt.decrypt("ChadDevz2Encrypt", ssh_user));
                edit.putString(Settings.SENHA_KEY, AESCrypt.decrypt("ChadDevz2Encrypt", ssh_pass));

            } catch (GeneralSecurityException e) {
            }

            edit.putString("ServerName", u);
            edit.putString("PayloadName", o);
            edit.putString(Settings.SERVIDOR_KEY, ssh_server);
            edit.putString(Settings.PROXY_IP_KEY, remote_proxy);
            edit.putString(Settings.PROXY_PORTA_KEY, proxy_port);

            boolean useCustomProxy =
                    config.getNetworksArray().getJSONObject(pos2).getBoolean("UseCustom");
            String custom_proxy =
                    config.getNetworksArray().getJSONObject(pos2).getString("CustomProxy");
            // String custom_proxy_port =
            // config.getNetworksArray().getJSONObject(pos2).getString("CustomProxy");

            if (useCustomProxy) {
                edit.putString(Settings.PROXY_IP_KEY, custom_proxy);
                edit.putString(Settings.SERVIDOR_KEY, custom_proxy);
                // edit.putString(Settings.PROXY_PORTA_KEY, custom_proxy_port);
                // edit.putString(Settings.SERVIDOR_PORTA_KEY, custom_proxy_port);
            }

            edit.apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadNetworks() {
        try {
            if (payloadList.size() > 0) {
                payloadList.clear();
                payloadAdapter.notifyDataSetChanged();
            }
            for (int i = 0; i < config.getNetworksArray().length(); i++) {
                JSONObject obj = config.getNetworksArray().getJSONObject(i);
                payloadList.add(obj);
                payloadAdapter.notifyDataSetChanged();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadServer() {
        try {
            if (serverList.size() > 0) {
                serverList.clear();
                serverAdapter.notifyDataSetChanged();
            }
            for (int i = 0; i < config.getServersArray().length(); i++) {
                JSONObject obj = config.getServersArray().getJSONObject(i);
                serverList.add(obj);
                serverAdapter.notifyDataSetChanged();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void doLayout() {
        setContentView(R.layout.activity_main_drawer);

        mChart = (LineChart) findViewById(R.id.chart1);

        graph =
                GraphHelper.getHelper()
                        .with(this)
                        .color(Color.parseColor(getString(R.color.colorPrimary)))
                        .chart(mChart);

        graph.start();

        liveData();

        htoRelativeLayout1 = findViewById(R.id.htoRelativeLayout1);
        htoRelativeLayout1.setVisibility(View.GONE);
        txt1 = (TextView) findViewById(R.id.toastxt);
        txt2 = (TextView) findViewById(R.id.btntoastxt);
        txt1.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 12);
        txt2.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 12);

        prefs = mConfig.getPrefsPrivate();
        SharedPreferences.Editor edit = prefs.edit();
        SharedPreferences sPrefs = mConfig.getPrefsPrivate();
        coins = PreferenceManager.getDefaultSharedPreferences(this);
        toolbar_main = findViewById(R.id.toolbar_main);
        navi = findViewById(R.id.navigation);
        drawer = findViewById(R.id.drawer);
        setSupportActionBar(toolbar_main);
        navi.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle =
                new ActionBarDrawerToggle(this, drawer, toolbar_main, R.string.bobo, R.string.bobo);
        toggle.syncState();

        starterButton = findViewById(R.id.activity_starterButtonMain);
        sPrefs.edit().putBoolean(Settings.PROXY_USAR_DEFAULT_PAYLOAD, false).apply();
        sPrefs.edit().putInt(Settings.TUNNELTYPE_KEY, Settings.bTUNNEL_TYPE_SSH_PROXY).apply();
        config = new ConfigUtil(this);
        connectionStatus = findViewById(R.id.connection_status);
        serverList = new ArrayList<>();
        payloadList = new ArrayList<>();
        serverSpinner = findViewById(R.id.serverSpinner);
        payloadSpinner = findViewById(R.id.payloadSpinner);
        serverAdapter = new SpinnerAdapter(this, R.id.serverSpinner, serverList);
        payloadAdapter = new PromoAdapter(this, R.id.payloadSpinner, payloadList);

        dosavedata();
        setSpinner();

        serverSpinner.setSelection(prefs.getInt("LastSelectedServer", 0));
        serverSpinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> p1, View p2, int p3, long p4) {

                        SharedPreferences prefs = mConfig.getPrefsPrivate();
                        SharedPreferences.Editor edit = prefs.edit();
                        edit.putInt("LastSelectedServer", p3).apply();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> p1) {}
                });

        payloadSpinner.setSelection(prefs.getInt("LastSelectedPayload", -1));
        payloadSpinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> p1, View p2, int p3, long p4) {

                        SharedPreferences prefs = mConfig.getPrefsPrivate();
                        SharedPreferences.Editor edit = prefs.edit();
                        int pos = payloadSpinner.getSelectedItemPosition();
                        edit.putInt("LastSelectedPayload", pos).apply();
                        // payloadSpinner.getSelectedItemPosition();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> p1) {}
                });

        serverSpinner.setAdapter(serverAdapter);
        payloadSpinner.setAdapter(payloadAdapter);
        payloadSpinner.setOnItemSelectedListener(this);

        updateConfig(true);
        configversion1 = findViewById(R.id.ConfigVersion1);
        starterButton.setOnClickListener(this);
        mTextViewCountDown = findViewById(R.id.timerTextView);
        tvMess = findViewById(R.id.tvMessage);
        mButtonSet = findViewById(R.id.btnAddTime);
        mButtonSet.setOnClickListener(this);
        payloadLayout = findViewById(R.id.activity_mainInputPayloadLinearLayout);
        payloadEdit = findViewById(R.id.activity_mainInputPayloadEditText);
        proxyInputLayout = findViewById(R.id.activity_mainInputProxyLayout);
        proxyText = findViewById(R.id.activity_mainProxyText);
        metodoConexaoRadio = findViewById(R.id.activity_mainMetodoConexaoRadio);
        customPayloadSwitch = findViewById(R.id.activity_mainCustomPayloadSwitch);
        configMsgLayout = findViewById(R.id.activity_mainMensagemConfigLinearLayout);
        configMsgText = findViewById(R.id.activity_mainMensagemConfigTextView);
        loginLayout = findViewById(R.id.activity_mainInputPasswordLayout);

        bytes_in_view = findViewById(R.id.bytes_in);
        bytes_out_view = findViewById(R.id.bytes_out);

        messLay = findViewById(R.id.messageLayout);
        payLay = findViewById(R.id.payloadLayout);
        sslLay = findViewById(R.id.sniLayout);
        edPayload = findViewById(R.id.edCustomPayload);
        edPayload.setText(prefs.getString("SavedHTTP", ""));
        edSsl = findViewById(R.id.edCustomSSL);
        edSsl.setText(prefs.getString("SavedSSL", ""));

        payLaySsl = findViewById(R.id.sslpayloadLayout);
        sslPayloadLay = findViewById(R.id.snipayloadLayout);
        sslPayload = findViewById(R.id.sslCustomPayload);
        sslPayload.setText(prefs.getString("SavedHTTP + SSL", ""));
        edSslpayload = findViewById(R.id.sniCustomSSL);
        edSslpayload.setText(prefs.getString("SavedSSL + HTTP", ""));

        edUserLayout = findViewById(R.id.UserLayout);
        edPassLayout = findViewById(R.id.PassLayout);
        webuser = findViewById(R.id.webUser);
        webuser.setText(prefs.getString("SavedUSER", ""));
        webpass = findViewById(R.id.webPass);
        webpass.setText(prefs.getString("SavedPASS", ""));

        bugsLayout = findViewById(R.id.bugLayout);
        bugremote = findViewById(R.id.webBug);
        bugremote.setText(prefs.getString("SavedBUG", ""));

        if (mConfig.getPrefsPrivate().getBoolean(Settings.CONFIG_PROTEGER_KEY, false)) {
        } else {
            payloadEdit.setText(mConfig.getPrivString(Settings.CUSTOM_PAYLOAD_KEY));
        }
        setupSpinner = findViewById(R.id.setupSpinner);
        SetupAdapter setupAdapter = new SetupAdapter(this, R.id.setupSpinner, setupList);
        setupSpinner.setAdapter(setupAdapter);
        setupSpinner.setSelection(prefs.getInt("SavedPos", 0));
        setupSpinner.setOnItemSelectedListener(
                new OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> p1, View p2, int p3, long p4) {
                        prefs.edit().putInt("SavedPos", p3).apply();
                        if (p3 == 0) {

                            bugsLayout.setVisibility(View.GONE);
                            bugremote.setVisibility(View.GONE);
                            edPayload.setVisibility(View.GONE);
                            edSsl.setVisibility(View.GONE);
                            payLay.setVisibility(View.GONE);
                            sslLay.setVisibility(View.GONE);
                            messLay.setVisibility(View.GONE);
                            sslPayload.setVisibility(View.GONE);
                            edSslpayload.setVisibility(View.GONE);
                            payLaySsl.setVisibility(View.GONE);
                            sslPayloadLay.setVisibility(View.GONE);
                            edUserLayout.setVisibility(View.GONE);
                            edPassLayout.setVisibility(View.GONE);
                            webuser.setVisibility(View.GONE);
                            webpass.setVisibility(View.GONE);
                        } else if (p3 == 1) {

                            edPayload.setVisibility(View.VISIBLE);
                            edSsl.setVisibility(View.GONE);
                            sslPayload.setVisibility(View.GONE);
                            edSslpayload.setVisibility(View.GONE);
                            payLaySsl.setVisibility(View.GONE);
                            sslPayloadLay.setVisibility(View.GONE);
                            payLay.setVisibility(View.VISIBLE);
                            sslLay.setVisibility(View.GONE);
                            messLay.setVisibility(View.GONE);
                            edUserLayout.setVisibility(View.GONE);
                            edPassLayout.setVisibility(View.GONE);
                            webuser.setVisibility(View.GONE);
                            webpass.setVisibility(View.GONE);
                            bugsLayout.setVisibility(View.GONE);
                            bugremote.setVisibility(View.GONE);

                        } else if (p3 == 2) {

                            edPayload.setVisibility(View.GONE);
                            edSsl.setVisibility(View.VISIBLE);
                            payLay.setVisibility(View.GONE);
                            sslLay.setVisibility(View.VISIBLE);
                            messLay.setVisibility(View.GONE);
                            sslPayload.setVisibility(View.GONE);
                            edSslpayload.setVisibility(View.GONE);
                            payLaySsl.setVisibility(View.GONE);
                            sslPayloadLay.setVisibility(View.GONE);
                            edUserLayout.setVisibility(View.GONE);
                            edPassLayout.setVisibility(View.GONE);
                            webuser.setVisibility(View.GONE);
                            webpass.setVisibility(View.GONE);
                            bugsLayout.setVisibility(View.GONE);
                            bugremote.setVisibility(View.GONE);

                        } else if (p3 == 3) {

                            prefs.edit()
                                    .putInt(Settings.TUNNELTYPE_KEY, Settings.bTUNNEL_TYPE_PAY_SSL)
                                    .apply();
                            sslPayload.setVisibility(View.VISIBLE);
                            edSslpayload.setVisibility(View.VISIBLE);
                            payLaySsl.setVisibility(View.VISIBLE);
                            sslPayloadLay.setVisibility(View.VISIBLE);
                            edUserLayout.setVisibility(View.VISIBLE);
                            edPassLayout.setVisibility(View.VISIBLE);
                            webuser.setVisibility(View.VISIBLE);
                            webpass.setVisibility(View.VISIBLE);
                            bugsLayout.setVisibility(View.VISIBLE);
                            bugremote.setVisibility(View.VISIBLE);
                            edPayload.setVisibility(View.GONE);
                            edSsl.setVisibility(View.GONE);
                            payLay.setVisibility(View.GONE);
                            sslLay.setVisibility(View.GONE);
                            messLay.setVisibility(View.GONE);

                        } else if (p3 == 4) {

                            edPayload.setVisibility(View.GONE);
                            edSsl.setVisibility(View.GONE);
                            payLay.setVisibility(View.GONE);
                            sslLay.setVisibility(View.VISIBLE);
                            messLay.setVisibility(View.GONE);
                            sslPayload.setVisibility(View.GONE);
                            edSslpayload.setVisibility(View.GONE);
                            payLaySsl.setVisibility(View.GONE);
                            sslPayloadLay.setVisibility(View.GONE);
                            edUserLayout.setVisibility(View.GONE);
                            edPassLayout.setVisibility(View.GONE);
                            webuser.setVisibility(View.GONE);
                            webpass.setVisibility(View.GONE);
                            bugsLayout.setVisibility(View.GONE);
                            bugremote.setVisibility(View.GONE);

                        } else if (p3 == 5) {

                            bugsLayout.setVisibility(View.GONE);
                            bugremote.setVisibility(View.GONE);
                            edUserLayout.setVisibility(View.GONE);
                            edPassLayout.setVisibility(View.GONE);
                            webuser.setVisibility(View.GONE);
                            webpass.setVisibility(View.GONE);
                            edPayload.setVisibility(View.GONE);
                            edSsl.setVisibility(View.GONE);
                            payLay.setVisibility(View.GONE);
                            sslLay.setVisibility(View.GONE);
                            sslPayload.setVisibility(View.GONE);
                            edSslpayload.setVisibility(View.GONE);
                            payLaySsl.setVisibility(View.GONE);
                            sslPayloadLay.setVisibility(View.GONE);
                            if (prefs.getBoolean("isMsg", false)) {
                                messLay.setVisibility(View.VISIBLE);
                                tvMess.setText(prefs.getString("Mess", ""));
                            }
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> p1) {}
                });

        imgFavorite = findViewById(R.id.ckSetup);
        imgFavorite.setChecked(prefs.getBoolean("SavedSetup", false));
        imgFavorite.setOnCheckedChangeListener(
                (p1, p2) -> {
                    prefs.edit().putBoolean("SavedSetup", p2).apply();
                    if (p2) {
                        payloadSpinner.setVisibility(View.GONE);
                        setupSpinner.setVisibility(View.VISIBLE);
                        int p3 = prefs.getInt("SavedPos", 0);
                        if (p3 == 0) {
                            prefs.edit()
                                    .putInt(
                                            Settings.TUNNELTYPE_KEY,
                                            Settings.bTUNNEL_TYPE_SSH_DIRECT)
                                    .apply();
                            edPayload.setVisibility(View.GONE);
                            edSsl.setVisibility(View.GONE);
                            payLay.setVisibility(View.GONE);
                            sslLay.setVisibility(View.GONE);
                            messLay.setVisibility(View.GONE);
                            sslPayload.setVisibility(View.GONE);
                            edSslpayload.setVisibility(View.GONE);
                            payLaySsl.setVisibility(View.GONE);
                            sslPayloadLay.setVisibility(View.GONE);
                            edUserLayout.setVisibility(View.GONE);
                            edPassLayout.setVisibility(View.GONE);
                            webuser.setVisibility(View.GONE);
                            webpass.setVisibility(View.GONE);
                            bugsLayout.setVisibility(View.GONE);
                            bugremote.setVisibility(View.GONE);

                        } else if (p3 == 1) {
                            bugsLayout.setVisibility(View.GONE);
                            bugremote.setVisibility(View.GONE);
                            edUserLayout.setVisibility(View.GONE);
                            edPassLayout.setVisibility(View.GONE);
                            webuser.setVisibility(View.GONE);
                            webpass.setVisibility(View.GONE);
                            edPayload.setVisibility(View.VISIBLE);
                            edSsl.setVisibility(View.GONE);
                            payLay.setVisibility(View.VISIBLE);
                            sslLay.setVisibility(View.GONE);
                            messLay.setVisibility(View.GONE);

                        } else if (p3 == 2) {
                            edUserLayout.setVisibility(View.GONE);
                            edPassLayout.setVisibility(View.GONE);
                            webuser.setVisibility(View.GONE);
                            webpass.setVisibility(View.GONE);
                            edPayload.setVisibility(View.GONE);
                            edSsl.setVisibility(View.VISIBLE);
                            payLay.setVisibility(View.GONE);
                            sslLay.setVisibility(View.VISIBLE);
                            messLay.setVisibility(View.GONE);
                            sslPayload.setVisibility(View.GONE);
                            edSslpayload.setVisibility(View.GONE);
                            payLaySsl.setVisibility(View.GONE);
                            sslPayloadLay.setVisibility(View.GONE);
                            bugsLayout.setVisibility(View.GONE);
                            bugremote.setVisibility(View.GONE);

                        } else if (p3 == 3) {
                            prefs.edit()
                                    .putInt(Settings.TUNNELTYPE_KEY, Settings.bTUNNEL_TYPE_PAY_SSL)
                                    .apply();
                            sslPayload.setVisibility(View.VISIBLE);
                            edSslpayload.setVisibility(View.VISIBLE);
                            payLaySsl.setVisibility(View.VISIBLE);
                            sslPayloadLay.setVisibility(View.VISIBLE);
                            edUserLayout.setVisibility(View.VISIBLE);
                            edPassLayout.setVisibility(View.VISIBLE);
                            webuser.setVisibility(View.VISIBLE);
                            webpass.setVisibility(View.VISIBLE);
                            bugsLayout.setVisibility(View.VISIBLE);
                            bugremote.setVisibility(View.VISIBLE);
                            edPayload.setVisibility(View.GONE);
                            edSsl.setVisibility(View.GONE);
                            payLay.setVisibility(View.GONE);
                            sslLay.setVisibility(View.GONE);
                            messLay.setVisibility(View.GONE);
                        } else if (p3 == 4) {
                            edPayload.setVisibility(View.GONE);
                            edSsl.setVisibility(View.GONE);
                            payLay.setVisibility(View.GONE);
                            sslLay.setVisibility(View.VISIBLE);
                            messLay.setVisibility(View.GONE);
                            sslPayload.setVisibility(View.GONE);
                            edSslpayload.setVisibility(View.GONE);
                            payLaySsl.setVisibility(View.GONE);
                            sslPayloadLay.setVisibility(View.GONE);
                            edUserLayout.setVisibility(View.GONE);
                            edPassLayout.setVisibility(View.GONE);
                            webuser.setVisibility(View.GONE);
                            webpass.setVisibility(View.GONE);
                            bugsLayout.setVisibility(View.GONE);
                            bugremote.setVisibility(View.GONE);
                        } else if (p3 == 5) {
                            bugsLayout.setVisibility(View.GONE);
                            bugremote.setVisibility(View.GONE);
                            edUserLayout.setVisibility(View.GONE);
                            edPassLayout.setVisibility(View.GONE);
                            webuser.setVisibility(View.GONE);
                            webpass.setVisibility(View.GONE);
                            edPayload.setVisibility(View.GONE);
                            edSsl.setVisibility(View.GONE);
                            payLay.setVisibility(View.GONE);
                            sslLay.setVisibility(View.GONE);
                            sslPayload.setVisibility(View.GONE);
                            edSslpayload.setVisibility(View.GONE);
                            payLaySsl.setVisibility(View.GONE);
                            sslPayloadLay.setVisibility(View.GONE);
                            if (prefs.getBoolean("isMsg", false)) {
                                messLay.setVisibility(View.VISIBLE);
                                tvMess.setText(prefs.getString("Mess", ""));
                            }
                        }
                    } else {

                        payloadSpinner.setVisibility(View.VISIBLE);
                        setupSpinner.setVisibility(View.GONE);
                        edPayload.setVisibility(View.GONE);
                        edSsl.setVisibility(View.GONE);
                        payLay.setVisibility(View.GONE);
                        sslLay.setVisibility(View.GONE);
                        messLay.setVisibility(View.GONE);
                        sslPayload.setVisibility(View.GONE);
                        edSslpayload.setVisibility(View.GONE);
                        payLaySsl.setVisibility(View.GONE);
                        sslPayloadLay.setVisibility(View.GONE);
                        edUserLayout.setVisibility(View.GONE);
                        edPassLayout.setVisibility(View.GONE);
                        webuser.setVisibility(View.GONE);
                        webpass.setVisibility(View.GONE);
                        bugsLayout.setVisibility(View.GONE);
                        bugremote.setVisibility(View.GONE);
                    }
                });

        customPayloadSwitch.setChecked(true);
        metodoConexaoRadio.setOnCheckedChangeListener(this);
        edit.putBoolean(Settings.PROXY_USAR_DEFAULT_PAYLOAD, !true);
        setPayloadSwitch(
                prefs.getInt(Settings.TUNNELTYPE_KEY, Settings.bTUNNEL_TYPE_SSH_DIRECT), true);
        int p3 = setupSpinner.getSelectedItemPosition();
        if (prefs.getBoolean("SavedSetup", false)) {
            setupSpinner.setVisibility(View.VISIBLE);
            payloadSpinner.setVisibility(View.GONE);
            if (p3 == 0) {
                prefs.edit()
                        .putInt(Settings.TUNNELTYPE_KEY, Settings.bTUNNEL_TYPE_SSH_DIRECT)
                        .apply();
                edPayload.setVisibility(View.GONE);
                edSsl.setVisibility(View.GONE);
                payLay.setVisibility(View.GONE);
                sslLay.setVisibility(View.GONE);
                edUserLayout.setVisibility(View.GONE);
                edPassLayout.setVisibility(View.GONE);
                webuser.setVisibility(View.GONE);
                webpass.setVisibility(View.GONE);
                messLay.setVisibility(View.GONE);
                sslPayload.setVisibility(View.GONE);
                edSslpayload.setVisibility(View.GONE);
                payLaySsl.setVisibility(View.GONE);
                sslPayloadLay.setVisibility(View.GONE);
                bugsLayout.setVisibility(View.GONE);
                bugremote.setVisibility(View.GONE);

            } else if (p3 == 1) {
                edUserLayout.setVisibility(View.GONE);
                edPassLayout.setVisibility(View.GONE);
                webuser.setVisibility(View.GONE);
                webpass.setVisibility(View.GONE);
                edPayload.setVisibility(View.VISIBLE);
                edSsl.setVisibility(View.GONE);
                payLay.setVisibility(View.VISIBLE);
                sslLay.setVisibility(View.GONE);
                messLay.setVisibility(View.GONE);
                sslPayload.setVisibility(View.GONE);
                edSslpayload.setVisibility(View.GONE);
                payLaySsl.setVisibility(View.GONE);
                sslPayloadLay.setVisibility(View.GONE);
                bugsLayout.setVisibility(View.GONE);
                bugremote.setVisibility(View.GONE);

            } else if (p3 == 2) {
                edUserLayout.setVisibility(View.GONE);
                edPassLayout.setVisibility(View.GONE);
                webuser.setVisibility(View.GONE);
                webpass.setVisibility(View.GONE);
                edPayload.setVisibility(View.GONE);
                edSsl.setVisibility(View.VISIBLE);
                payLay.setVisibility(View.GONE);
                sslLay.setVisibility(View.VISIBLE);
                messLay.setVisibility(View.GONE);
                sslPayload.setVisibility(View.GONE);
                edSslpayload.setVisibility(View.GONE);
                payLaySsl.setVisibility(View.GONE);
                sslPayloadLay.setVisibility(View.GONE);
                bugsLayout.setVisibility(View.GONE);
                bugremote.setVisibility(View.GONE);

            } else if (p3 == 3) {
                prefs.edit().putInt(Settings.TUNNELTYPE_KEY, Settings.bTUNNEL_TYPE_PAY_SSL).apply();
                edPayload.setVisibility(View.GONE);
                edSsl.setVisibility(View.GONE);
                payLay.setVisibility(View.GONE);
                sslLay.setVisibility(View.GONE);
                messLay.setVisibility(View.GONE);
                sslPayload.setVisibility(View.VISIBLE);
                edSslpayload.setVisibility(View.VISIBLE);
                payLaySsl.setVisibility(View.VISIBLE);
                sslPayloadLay.setVisibility(View.VISIBLE);
                edUserLayout.setVisibility(View.VISIBLE);
                edPassLayout.setVisibility(View.VISIBLE);
                webuser.setVisibility(View.VISIBLE);
                webpass.setVisibility(View.VISIBLE);
                bugsLayout.setVisibility(View.VISIBLE);
                bugremote.setVisibility(View.VISIBLE);

            } else if (p3 == 4) {

                edPayload.setVisibility(View.GONE);
                edSsl.setVisibility(View.GONE);
                payLay.setVisibility(View.GONE);
                sslLay.setVisibility(View.VISIBLE);
                messLay.setVisibility(View.GONE);
                sslPayload.setVisibility(View.GONE);
                edSslpayload.setVisibility(View.GONE);
                payLaySsl.setVisibility(View.GONE);
                sslPayloadLay.setVisibility(View.GONE);
                edUserLayout.setVisibility(View.GONE);
                edPassLayout.setVisibility(View.GONE);
                webuser.setVisibility(View.GONE);
                webpass.setVisibility(View.GONE);
                bugsLayout.setVisibility(View.GONE);
                bugremote.setVisibility(View.GONE);
            } else if (p3 == 5) {

                bugsLayout.setVisibility(View.GONE);
                bugremote.setVisibility(View.GONE);
                edUserLayout.setVisibility(View.GONE);
                edPassLayout.setVisibility(View.GONE);
                webuser.setVisibility(View.GONE);
                webpass.setVisibility(View.GONE);
                edPayload.setVisibility(View.GONE);
                edSsl.setVisibility(View.GONE);
                payLay.setVisibility(View.GONE);
                sslLay.setVisibility(View.GONE);
                sslPayload.setVisibility(View.GONE);
                edSslpayload.setVisibility(View.GONE);
                payLaySsl.setVisibility(View.GONE);
                sslPayloadLay.setVisibility(View.GONE);
                if (prefs.getBoolean("isMsg", false)) {
                    messLay.setVisibility(View.VISIBLE);
                    tvMess.setText(prefs.getString("Mess", ""));
                }
            }
            //   configMessageLay.setVisibility(View.GONE);
        } else {

            setupSpinner.setVisibility(View.GONE);
            payloadSpinner.setVisibility(View.VISIBLE);
            edPayload.setVisibility(View.GONE);
            edSsl.setVisibility(View.GONE);
            payLay.setVisibility(View.GONE);
            sslLay.setVisibility(View.GONE);
            messLay.setVisibility(View.GONE);
            sslPayload.setVisibility(View.GONE);
            edSslpayload.setVisibility(View.GONE);
            payLaySsl.setVisibility(View.GONE);
            sslPayloadLay.setVisibility(View.GONE);
            edUserLayout.setVisibility(View.GONE);
            edPassLayout.setVisibility(View.GONE);
            webuser.setVisibility(View.GONE);
            webpass.setVisibility(View.GONE);
            bugsLayout.setVisibility(View.GONE);
            bugremote.setVisibility(View.GONE);
        }

        if (imgFavorite.isChecked()) {
            if (p3 == 0) {
                prefs.edit()
                        .putInt(Settings.TUNNELTYPE_KEY, Settings.bTUNNEL_TYPE_SSH_DIRECT)
                        .apply();
                edPayload.setVisibility(View.GONE);
                edSsl.setVisibility(View.GONE);
                payLay.setVisibility(View.GONE);
                sslLay.setVisibility(View.GONE);
                messLay.setVisibility(View.GONE);
                sslPayload.setVisibility(View.GONE);
                edSslpayload.setVisibility(View.GONE);
                payLaySsl.setVisibility(View.GONE);
                sslPayloadLay.setVisibility(View.GONE);
                edUserLayout.setVisibility(View.GONE);
                edPassLayout.setVisibility(View.GONE);
                webuser.setVisibility(View.GONE);
                webpass.setVisibility(View.GONE);
                bugsLayout.setVisibility(View.GONE);
                bugremote.setVisibility(View.GONE);

            } else if (p3 == 1) {
                edPayload.setVisibility(View.VISIBLE);
                edSsl.setVisibility(View.GONE);
                payLay.setVisibility(View.VISIBLE);
                sslLay.setVisibility(View.GONE);
                messLay.setVisibility(View.GONE);
                sslPayload.setVisibility(View.GONE);
                edSslpayload.setVisibility(View.GONE);
                payLaySsl.setVisibility(View.GONE);
                sslPayloadLay.setVisibility(View.GONE);
                edUserLayout.setVisibility(View.GONE);
                edPassLayout.setVisibility(View.GONE);
                webuser.setVisibility(View.GONE);
                webpass.setVisibility(View.GONE);
                bugsLayout.setVisibility(View.GONE);
                bugremote.setVisibility(View.GONE);

            } else if (p3 == 2) {
                edPayload.setVisibility(View.GONE);
                edSsl.setVisibility(View.VISIBLE);
                payLay.setVisibility(View.GONE);
                sslLay.setVisibility(View.VISIBLE);
                messLay.setVisibility(View.GONE);
                sslPayload.setVisibility(View.GONE);
                edSslpayload.setVisibility(View.GONE);
                payLaySsl.setVisibility(View.GONE);
                sslPayloadLay.setVisibility(View.GONE);
                edUserLayout.setVisibility(View.GONE);
                edPassLayout.setVisibility(View.GONE);
                webuser.setVisibility(View.GONE);
                webpass.setVisibility(View.GONE);
                bugsLayout.setVisibility(View.GONE);
                bugremote.setVisibility(View.GONE);

            } else if (p3 == 3) {
                prefs.edit().putInt(Settings.TUNNELTYPE_KEY, Settings.bTUNNEL_TYPE_PAY_SSL).apply();
                edPayload.setVisibility(View.GONE);
                edSsl.setVisibility(View.GONE);
                payLay.setVisibility(View.GONE);
                sslLay.setVisibility(View.GONE);
                messLay.setVisibility(View.GONE);
                sslPayload.setVisibility(View.VISIBLE);
                edSslpayload.setVisibility(View.VISIBLE);
                payLaySsl.setVisibility(View.VISIBLE);
                sslPayloadLay.setVisibility(View.VISIBLE);
                edUserLayout.setVisibility(View.VISIBLE);
                edPassLayout.setVisibility(View.VISIBLE);
                webuser.setVisibility(View.VISIBLE);
                webpass.setVisibility(View.VISIBLE);
                bugsLayout.setVisibility(View.VISIBLE);
                bugremote.setVisibility(View.VISIBLE);

            } else if (p3 == 4) {

                edPayload.setVisibility(View.GONE);
                edSsl.setVisibility(View.GONE);
                payLay.setVisibility(View.GONE);
                sslLay.setVisibility(View.VISIBLE);
                messLay.setVisibility(View.GONE);
                sslPayload.setVisibility(View.GONE);
                edSslpayload.setVisibility(View.GONE);
                payLaySsl.setVisibility(View.GONE);
                sslPayloadLay.setVisibility(View.GONE);
                edUserLayout.setVisibility(View.GONE);
                edPassLayout.setVisibility(View.GONE);
                webuser.setVisibility(View.GONE);
                webpass.setVisibility(View.GONE);
                bugsLayout.setVisibility(View.GONE);
                bugremote.setVisibility(View.GONE);
            } else if (p3 == 5) {

                bugsLayout.setVisibility(View.GONE);
                bugremote.setVisibility(View.GONE);
                edUserLayout.setVisibility(View.GONE);
                edPassLayout.setVisibility(View.GONE);
                webuser.setVisibility(View.GONE);
                webpass.setVisibility(View.GONE);
                edPayload.setVisibility(View.GONE);
                edSsl.setVisibility(View.GONE);
                payLay.setVisibility(View.GONE);
                sslLay.setVisibility(View.GONE);
                sslPayload.setVisibility(View.GONE);
                edSslpayload.setVisibility(View.GONE);
                payLaySsl.setVisibility(View.GONE);
                sslPayloadLay.setVisibility(View.GONE);
                if (prefs.getBoolean("isMsg", false)) {
                    messLay.setVisibility(View.VISIBLE);
                    tvMess.setText(prefs.getString("Mess", ""));
                }
            }
        }
    }

    void isRunning(boolean z) {
        if (z) {
            payloadSpinner.setEnabled(false);
            serverSpinner.setEnabled(false);
            setupSpinner.setEnabled(false);
            imgFavorite.setEnabled(false);
            edPayload.setEnabled(false);
            edSsl.setEnabled(false);
            sslPayload.setEnabled(false);
            edSslpayload.setEnabled(false);
            webuser.setEnabled(false);
            webpass.setEnabled(false);
            bugremote.setEnabled(false);
        } else {
            payloadSpinner.setEnabled(true);
            serverSpinner.setEnabled(true);
            setupSpinner.setEnabled(true);
            imgFavorite.setEnabled(true);
            edPayload.setEnabled(true);
            edSsl.setEnabled(true);
            sslPayload.setEnabled(true);
            edSslpayload.setEnabled(true);
            webuser.setEnabled(true);
            webpass.setEnabled(true);
            bugremote.setEnabled(true);
        }
    }

    private void liveDataS() {
        new Timer()
                .schedule(
                        new TimerTask() {
                            @RequiresApi(api = Build.VERSION_CODES.N)
                            @Override
                            public void run() {
                                mHandler.post(
                                        () -> {
                                            getData();
                                        });
                            }
                        },
                        0,
                        1000);
    }

    private void liveData() {
        dataUpdate =
                new Thread(
                        new Runnable() {
                            @Override
                            public void run() {

                                while (!dataUpdate.getName().equals("stopped")) {

                                    fHandler.post(
                                            new Runnable() {

                                                // private static final long xup = 0;

                                                @Override
                                                public void run() {
                                                    if (toString().equals("Connected")) {
                                                        graph.start();
                                                    }
                                                }
                                            });

                                    try {
                                        Thread.sleep(1000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    //  progressStatus--;
                                }
                            }
                        });

        dataUpdate.setName("started");
        dataUpdate.start();
    }

    final class MyThreadClass implements Runnable {
        @Override
        public void run() {
            int i = 0;
            synchronized (this) {
                while (dataThread.getName() == "showDataGraph") {
                    //  Log.e("insidebroadcast", Integer.toString(service_id) + " " +
                    // Integer.toString(i));
                    getData2();
                    try {
                        wait(1000);
                        i++;
                    } catch (InterruptedException e) {
                        // sshMsg(e.getMessage());
                    }
                }
                // stopSelf(service_id);
            }
        }
    }

    public void getData2() {
        List<Long> allData;
        allData = RetrieveData.findData();
        long mDownload = DataTransferGraph2.download;
        long mUpload = DataTransferGraph2.upload;
        mDownload = allData.get(0);
        mUpload = allData.get(1);
        storedData2(mUpload, mDownload);
    }

    public void storedData2(Long mUpload, Long mDownload) {
        StoredData.downloadSpeed = mDownload;
        StoredData.uploadSpeed = mUpload;
        if (StoredData.isSetData) {
            StoredData.downloadList.remove(0);
            StoredData.uploadList.remove(0);
            StoredData.downloadList.add(mDownload);
            StoredData.uploadList.add(mUpload);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void getData() {
        boolean isRunning = SkStatus.isTunnelActive();
        long mUpload, mDownload, saved_Send, saved_Down /*,up, down*/;
        String saved_date, tDate;
        List<Long> allData;
        allData = RetrieveData.findData();
        mDownload = allData.get(0);
        mUpload = allData.get(1);
        Stored.storedData(mDownload, mUpload);
        Calendar ca = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
        tDate = sdf.format(ca.getTime());
        saved_date = myData.getString("today_date", "empty");
        SharedPreferences.Editor editor = myData.edit();
        if (saved_date.equals(tDate)) {
            saved_Send = myData.getLong("UP_DATA", 0);
            saved_Down = myData.getLong("DOWN_DATA", 0);
            editor.putLong("UP_DATA", mUpload + saved_Send);
            editor.putLong("DOWN_DATA", mDownload + saved_Down);
            editor.apply();
        } else {
            editor.clear();
            editor.putString("today_date", tDate);
            editor.apply();
        }
        if (isRunning) {
            bytes_out_view.setText(render_bandwidth(myData.getLong("UP_DATA", 0)));
            bytes_in_view.setText(render_bandwidth(myData.getLong("DOWN_DATA", 0)));
        } else {
            myData.edit().putLong("UP_DATA", 0).apply();
            myData.edit().putLong("DOWN_DATA", 0).apply();
        }
        StatisticGraphData.DataTransferStats stats =
                StatisticGraphData.getStatisticData().getDataTransferStats();
        String duration =
                stats.isConnected()
                        ? stats.elapsedTimeToDisplay(stats.getElapsedTime())
                        : "00h:00m:00s";
    }

    private String render_bandwidth(double bw) {
        String postfix;
        float div;
        Object[] objArr;
        float bwf = (float) bw;
        if (bwf >= 1.0E12f) {
            postfix = "TB";
            div = 1.0995116E12f;
        } else if (bwf >= 1.0E9f) {
            postfix = "GB";
            div = 1.0737418E9f;
        } else if (bwf >= 1000000.0f) {
            postfix = "MB";
            div = 1048576.0f;
        } else if (bwf >= 1000.0f) {
            postfix = "KB";
            div = 1024.0f;
        } else {
            objArr = new Object[S_BIND_CALLED];
            objArr[0] = Float.valueOf(bwf);
            return String.format("%.0f", objArr);
        }
        objArr = new Object[S_ONSTART_CALLED];
        objArr[0] = Float.valueOf(bwf / div);
        objArr[S_BIND_CALLED] = postfix;
        return String.format("%.2f %s", objArr);
    }

    private void setPayloadSwitch(int tunnelType, boolean isCustomPayload) {
        SharedPreferences prefs = mConfig.getPrefsPrivate();

        boolean isRunning = SkStatus.isTunnelActive();

        customPayloadSwitch.setChecked(isCustomPayload);

        if (prefs.getBoolean(Settings.CONFIG_PROTEGER_KEY, false)) {
            payloadEdit.setEnabled(false);

            if (mConfig.getPrivString(Settings.CUSTOM_PAYLOAD_KEY).isEmpty()) {
                customPayloadSwitch.setEnabled(false);
            } else {
                customPayloadSwitch.setEnabled(!isRunning);
            }

            if (!isCustomPayload && tunnelType == Settings.bTUNNEL_TYPE_SSH_PROXY)
                payloadEdit.setText(Settings.PAYLOAD_DEFAULT);
            else payloadEdit.setText("*******");
        } else {
            customPayloadSwitch.setEnabled(!isRunning);

            if (isCustomPayload) {
                payloadEdit.setText(mConfig.getPrivString(Settings.CUSTOM_PAYLOAD_KEY));
                payloadEdit.setEnabled(!isRunning);
            } else if (tunnelType == Settings.bTUNNEL_TYPE_SSH_PROXY) {
                payloadEdit.setText(Settings.PAYLOAD_DEFAULT);
                payloadEdit.setEnabled(true);
            }
        }

        if (isCustomPayload || tunnelType == Settings.bTUNNEL_TYPE_SSH_PROXY) {
            payloadLayout.setVisibility(View.VISIBLE);
        } else {
            payloadLayout.setVisibility(View.GONE);
        }
    }

    private void tuts() {
        LayoutInflater inflater =
                (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View inflate = inflater.inflate(R.layout.notif, null);
        AlertDialog.Builder builer = new AlertDialog.Builder(this);
        builer.setView(inflate);
        final TextView title = inflate.findViewById(R.id.notiftext1);
        TextView ms = inflate.findViewById(R.id.confimsg);
        final TextView bubu = inflate.findViewById(R.id.appButton1);
        TextView cancel = inflate.findViewById(R.id.appButton2);
        title.setText("How to Connect?");
        ms.setText(
                "Globe TM Tutorial\nhttps://fb.watch/gZAvP6QEBa\n\nSmart TNT Sun\nhttps://fb.watch/gZACTYZy6V");
        bubu.setText("Ok,Close");
        cancel.setText("Exit");
        final AlertDialog alert = builer.create();
        alert.setCanceledOnTouchOutside(false);
        alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alert.getWindow().setGravity(Gravity.CENTER);
        alert.show();
        bubu.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            alert.dismiss();

                        } catch (Exception ignored) {
                        }
                    }
                });
        cancel.setOnClickListener(
                v -> {
                    //
                    alert.dismiss();
                });
        alert.show();
    }

    private void doUpdateLayout() {

        // updateConfig(true);

        configversion1.setText("" + config.getVersion());

        SharedPreferences mPref =
                PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences prefs = mConfig.getPrefsPrivate();

        boolean isRunning = SkStatus.isTunnelActive();
        int tunnelType = prefs.getInt(Settings.TUNNELTYPE_KEY, Settings.bTUNNEL_TYPE_SSH_DIRECT);
        isRunning(isRunning);
        setStarterButton(starterButton, this);
        setPayloadSwitch(tunnelType, !prefs.getBoolean(Settings.PROXY_USAR_DEFAULT_PAYLOAD, true));
        isRunning(isRunning);
        String proxyStr = getText(R.string.no_value).toString();

        if (prefs.getBoolean(Settings.CONFIG_PROTEGER_KEY, false)) {
            proxyStr = "*******";
            proxyInputLayout.setEnabled(false);
        } else {
            String proxy = mConfig.getPrivString(Settings.PROXY_IP_KEY);

            if (proxy != null && !proxy.isEmpty())
                proxyStr =
                        String.format(
                                "%s:%s", proxy, mConfig.getPrivString(Settings.PROXY_PORTA_KEY));
            proxyInputLayout.setEnabled(!isRunning);
        }

        proxyText.setText(proxyStr);

        setPayloadSwitch(tunnelType, !prefs.getBoolean(Settings.PROXY_USAR_DEFAULT_PAYLOAD, true));

        switch (tunnelType) {
            case Settings.bTUNNEL_TYPE_SSH_DIRECT:
                customPayloadSwitch.setChecked(true);
                break;

            case Settings.bTUNNEL_TYPE_SSH_PROXY:
                customPayloadSwitch.setChecked(true);
                break;

            case Settings.bTUNNEL_TYPE_SSH_SSLTUNNEL:
                customPayloadSwitch.setChecked(false);
                break;

            case Settings.bTUNNEL_TYPE_PAY_SSL:
                customPayloadSwitch.setChecked(true);
                break;
            case Settings.bTUNNEL_TYPE_SSL_RP:
                customPayloadSwitch.setChecked(true);
                break;
            case Settings.bTUNNEL_TYPE_SLOWDNS:
                customPayloadSwitch.setChecked(false);
                break;
        }

        int msgVisibility = View.GONE;
        int loginVisibility = View.GONE;
        String msgText = "";
        boolean enabled_radio = !isRunning;

        if (prefs.getBoolean(Settings.CONFIG_PROTEGER_KEY, false)) {

            if (prefs.getBoolean(Settings.CONFIG_INPUT_PASSWORD_KEY, false)) {
                loginVisibility = View.VISIBLE;
            }

            String msg = mConfig.getPrivString(Settings.CONFIG_MENSAGEM_KEY);
            if (!msg.isEmpty()) {
                msgText = msg.replace("\n", "<br/>");
                msgVisibility = View.VISIBLE;
            }

            if (mConfig.getPrivString(Settings.PROXY_IP_KEY).isEmpty()
                    || mConfig.getPrivString(Settings.PROXY_PORTA_KEY).isEmpty()) {
                enabled_radio = false;
            }
        }

        loginLayout.setVisibility(loginVisibility);
        configMsgText.setText(msgText.isEmpty() ? "" : Html.fromHtml(msgText));
        configMsgLayout.setVisibility(msgVisibility);

        for (int i = 0; i < metodoConexaoRadio.getChildCount(); i++) {
            metodoConexaoRadio.getChildAt(i).setEnabled(enabled_radio);
        }
    }

    public void customToast(String t1, String t2) {
        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.abc_fade_in);
        htoRelativeLayout1.setVisibility(View.VISIBLE);
        txt1.setText(t1);
        txt2.setText(t2);
        new Handler()
                .postDelayed(
                        new Runnable() {
                            @Override
                            public void run() {
                                htoRelativeLayout1.setVisibility(View.GONE);
                            }
                        },
                        5000);
        htoRelativeLayout1.startAnimation(anim);
    }

    private void updateConfig(final boolean isOnCreate) {
        new ConfigUpdate(
                        this,
                        new ConfigUpdate.OnUpdateListener() {
                            @Override
                            public void onUpdateListener(String result) {
                                try {
                                    if (!result.contains("Error on getting data")) {
                                        String json_data =
                                                AESCrypt.decrypt(
                                                        config.abcdefghijklmnopqrstuvwxyz, result);
                                        if (isNewVersion(json_data)) {
                                            newUpdateDialog(result);
                                        } else {
                                            if (!isOnCreate) {
                                                customToast(
                                                        "No Updates Available you are in latest Version.",
                                                        "");
                                            }
                                        }
                                    } else if (result.contains("Error on getting data")
                                            && !isOnCreate) {
                                        customToast(
                                                "Failed To Update Servers Please try Again.", "");
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        })
                .start(isOnCreate);
    }

    private boolean isNewVersion(String result) {
        try {
            String current = config.getVersion();
            String update = new JSONObject(result).getString("Version");
            return config.versionCompare(update, current);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void restart_app() {
        int flags =
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
                        ? PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_CANCEL_CURRENT
                        : PendingIntent.FLAG_CANCEL_CURRENT;
        Intent intent = new Intent(this, MainActivity.class);
        int i = 123456;
        PendingIntent pendingIntent = PendingIntent.getActivity(this, i, intent, flags);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(
                AlarmManager.RTC, System.currentTimeMillis() + ((long) 1000), pendingIntent);
        finish();
    }

    private void newUpdateDialog(final String result)
            throws JSONException, GeneralSecurityException {
        String json_data = AESCrypt.decrypt(ConfigUtil.abcdefghijklmnopqrstuvwxyz, result);
        String notes = new JSONObject(json_data).getString("ReleaseNotes");
        LayoutInflater inflater =
                (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View inflate = inflater.inflate(R.layout.update_dial, null);
        MaterialAlertDialogBuilder builder =
                new MaterialAlertDialogBuilder(this, R.style.MaterialAlertDialogText);
        builder.setView(inflate);
        final TextView title = inflate.findViewById(R.id.notiftext1);
        TextView ms = inflate.findViewById(R.id.confimsg);
        title.setText("Update Available");
        
        // Add the custom toast here
        customToast("Please Update Your Config", "");

        ms.setText(notes);

        builder.setPositiveButton(
                "Update",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            dialog.dismiss();
                            File file = new File(getFilesDir(), "Config.json");
                            OutputStream out = new FileOutputStream(file);
                            out.write(result.getBytes());
                            out.flush();
                            out.close();
                            restart_app();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

        builder.setNegativeButton(
                "Cancel",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        AlertDialog alert = builder.create();
        alert.setCanceledOnTouchOutside(true);
        alert.show();
    }

    public void Changelogs() {
        LayoutInflater inflater =
                (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View inflate = inflater.inflate(R.layout.notif, (ViewGroup) null);
        AlertDialog.Builder builer = new AlertDialog.Builder(this);
        builer.setView(inflate);
        TextView title = inflate.findViewById(R.id.notiftext1);
        TextView ms = inflate.findViewById(R.id.confimsg);
        TextView cancel = inflate.findViewById(R.id.appButton2);
        Button ok = inflate.findViewById(R.id.appButton1);
        title.setText("Release Note");
        ms.setText(this.config.geNote());
        cancel.setText("Exit");
        final AlertDialog alert = builer.create();
        alert.setCanceledOnTouchOutside(false);
        alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alert.getWindow().setGravity(Gravity.CENTER);
        /// alert.getWindow().getAttributes().windowAnimations = R.style.dialog;
        alert.show();
        ok.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View p1) {
                        updateConfig(false);
                    }
                });
        cancel.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            alert.dismiss();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

        alert.show();
    }

    public static void updateMainViews(Context context) {
        Intent updateView = new Intent(UPDATE_VIEWS);
        LocalBroadcastManager.getInstance(context).sendBroadcast(updateView);
    }

    public void startOrStopTunnel(Activity activity) {
        if (SkStatus.isTunnelActive()) {
            TunnelManagerHelper.stopSocksHttp(activity);
        } else {
            Settings config = new Settings(activity);
            Intent intent = new Intent(activity, LaunchVpn.class);
            intent.setAction(Intent.ACTION_MAIN);
            if (config.getHideLog()) {
                intent.putExtra(LaunchVpn.EXTRA_HIDELOG, true);
            }
            activity.startActivity(intent);
        }
    }

    public void setStarterButton(Button starterButton, Activity activity) {
        String state = SkStatus.getLastState();
        boolean isRunning = SkStatus.isTunnelActive();

        if (starterButton != null) {
            int resId;

            SharedPreferences prefsPrivate = new Settings(activity).getPrefsPrivate();

            if (ConfigParser.isValidadeExpirou(
                    prefsPrivate.getLong(Settings.CONFIG_VALIDADE_KEY, 0))) {
                resId = R.string.expired;
                starterButton.setEnabled(false);

                if (isRunning) {
                    startOrStopTunnel(activity);
                }
            } else if (SkStatus.SSH_INICIANDO.equals(state)) {
                resId = R.string.stop;

                dataThread = new Thread(new MyThreadClass());
                dataThread.setName("showDataGraph");
                dataThread.start();
                start();
                starterButton.setEnabled(false);
            } else if (SkStatus.SSH_PARANDO.equals(state)) {
                resId = R.string.state_stopping;

                dataThread = new Thread(new MyThreadClass());
                dataThread.setName("stopDataGraph");
                starterButton.setEnabled(false);

                starterButton.setEnabled(false);
            } else {
                resId = isRunning ? R.string.stop : R.string.start;
                starterButton.setEnabled(true);
            }
            starterButton.setText(resId);
        }
    }

    @Override
    public void onClick(View p1) {

        switch (p1.getId()) {
            case R.id.activity_starterButtonMain:
                dosavedata();
                loadServerData();
                startOrStopTunnel(this);
                break;

            case R.id.btnAddTime:
                loadRewardedAd();
                loadingAds();
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    public void updateState(
            final String state,
            String msg,
            int localizedResId,
            final ConnectionStatus level,
            Intent intent) {
        mHandler.post(
                () -> {
                    doUpdateLayout();
                    if (SkStatus.isTunnelActive()) {
                        if (level.equals(ConnectionStatus.LEVEL_CONNECTED)) {

                            if (dontme.isDeviceRooted()) {
                                Intent intent1 = getIntent();
                                finish();
                                startActivity(intent1);
                            }
                            showInterstitial();
                            connectionStatus.setText(R.string.connected);
                            customToast("Conneced Successfully", "");
                        }
                        if (level.equals(ConnectionStatus.LEVEL_NOTCONNECTED)) {}

                        if (level.equals(ConnectionStatus.LEVEL_CONNECTING_SERVER_REPLIED)) {
                            connectionStatus.setText(R.string.connecting);
                        }

                        if (level.equals(ConnectionStatus.LEVEL_CONNECTING_NO_SERVER_REPLY_YET)) {}
                        if (level.equals(ConnectionStatus.LEVEL_AUTH_FAILED)) {}
                        if (level.equals(ConnectionStatus.UNKNOWN_LEVEL)) {
                            connectionStatus.setText(R.string.disconnected);
                            customToast("Disconnected Successfully", "");
                            showInterstitial();
                            stop();
                        }
                        if (level.equals(ConnectionStatus.LEVEL_NONETWORK)) {}
                    }
                });

        switch (state) {
            case SkStatus.SSH_CONECTADO:
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.miExit:
                /*  Intent status = new Intent(this, ServerStatusActivity.class);
                startActivity(status); */
                offlineUpdate();
                break;
            case R.id.settingmain:
                Intent wifif = new Intent(this, ConfigGeralActivity.class);
                startActivity(wifif);
                break;
            case R.id.mRenew:
                startActivity(
                        new Intent(
                                "android.intent.action.VIEW",
                                Uri.parse("https://m.me/sindayen.reymar.7")));
                break;
            case R.id.miLimparLogs:
                logClear();
                // Snackbar.make(Html.fromHtml(findViewById(R.id.snackbar), "<strong><font
                // color='#04D000'>Log Clear</font></strong>",-1).show());
                // mNotifyBuilder.setContentText(Html.fromHtml("<font
                // color='#B71C1C'>Download↓</font>" + DataTransferGraph.Count(sdown, true) + " / "
                // + DataTransferGraph.Count(download, false) + " |<font
                // color='#304FFE'>Upload↑</font>" + DataTransferGraph.Count(sup, true) + " / " +
                // DataTransferGraph.Count(upload, false)));

                break;

            case R.id.miCopyLogs:
                int sdk = android.os.Build.VERSION.SDK_INT;
                if (sdk < android.os.Build.VERSION_CODES.HONEYCOMB) {
                    android.text.ClipboardManager clipboard =
                            (android.text.ClipboardManager)
                                    getSystemService(Context.CLIPBOARD_SERVICE);
                    clipboard.setText(getLog());
                } else {
                    android.content.ClipboardManager clipboard =
                            (android.content.ClipboardManager)
                                    getSystemService(Context.CLIPBOARD_SERVICE);
                    android.content.ClipData clip =
                            android.content.ClipData.newPlainText("text label", getLog());
                    clipboard.setPrimaryClip(clip);
                }

                break;

            case R.id.help:
                help();
                break;

            case R.id.darkmode:
                darkModes();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        LayoutInflater inflater =
                (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View inflate = inflater.inflate(R.layout.c, (ViewGroup) null);
        AlertDialog.Builder builer = new AlertDialog.Builder(this);
        builer.setView(inflate);
        TextView title = inflate.findViewById(R.id.notiftext1);
        TextView ms = inflate.findViewById(R.id.confimsg);
        TextView bubu = inflate.findViewById(R.id.appButton1);
        TextView cancel = inflate.findViewById(R.id.appButton2);
        title.setText("Attention!");
        ms.setText("Are you sure you want to exit?");
        bubu.setText("Minimize");
        cancel.setText("Exit");
        final AlertDialog alert = builer.create();
        alert.setCanceledOnTouchOutside(true);
        alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alert.getWindow().setGravity(Gravity.CENTER);
        // alert.getWindow().getAttributes().windowAnimations = R.style.dialog;
        alert.show();
        bubu.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            Intent startMain = new Intent(Intent.ACTION_MAIN);
                            startMain.addCategory(Intent.CATEGORY_HOME);
                            startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(startMain);
                            alert.dismiss();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
        cancel.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alert.dismiss();
                        Utils.exitAll(MainActivity.this);
                    }
                });
        alert.show();
    }

    /*public void Changelogs()
       {
    	View inflate = LayoutInflater.from(this).inflate(R.layout.notif, null);
           MaterialAlertDialogBuilder alertDialogBuilder = new MaterialAlertDialogBuilder(this);
    	alertDialogBuilder.setView(inflate);
    	TextView title = inflate.findViewById(R.id.notiftext1);
    	TextView ms = inflate.findViewById(R.id.confimsg);
    	Button ok = inflate.findViewById(R.id.appButton1);
    	title.setText("Release Notes!");
    	ms.setText(this.config.geNote());
    	ok.setText("Hide");
    	final AlertDialog alert = alertDialogBuilder.create();
    	alert.setCanceledOnTouchOutside(false);
    	alert.getWindow().setGravity(Gravity.CENTER);
    	ok.setOnClickListener(new View.OnClickListener() {
    			@Override
    			public void onClick(View p1){
    				alert.dismiss();
                       showInterstitial();
    			}
    		});
    	alert.show();
    }*/

    private void loadingAds() {
        LayoutInflater inflater =
                (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View inflate = inflater.inflate(R.layout.progress, (ViewGroup) null);
        MaterialAlertDialogBuilder builer = new MaterialAlertDialogBuilder(this);
        builer.setView(inflate);
        ProgressBar pg = inflate.findViewById(R.id.progress);
        TextView title = inflate.findViewById(R.id.loading);
        pg.getProgress();
        title.setText("Loading please wait...");
        alert1 = builer.create();
        alert1.setCancelable(false);
        alert1.getWindow().getAttributes().windowAnimations = R.style.SlidingDialogAnimation;
        alert1.show();
    }

    private void loadAd() {
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(
                MainActivity.this,
                getString(R.string.ad_interstitial_home),
                adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        MainActivity.this.interstitialAd = interstitialAd;
                        interstitialAd.setFullScreenContentCallback(
                                new FullScreenContentCallback() {

                                    @Override
                                    public void onAdDismissedFullScreenContent() {
                                        MainActivity.this.interstitialAd = null;
                                        // Toast.makeText(MainActivity.this, "Thank you for
                                        // supporting the app !! 💙", Toast.LENGTH_SHORT).show();
                                        loadAd();
                                    }

                                    @Override
                                    public void onAdFailedToShowFullScreenContent(AdError adError) {
                                        MainActivity.this.interstitialAd = null;
                                    }

                                    @Override
                                    public void onAdShowedFullScreenContent() {}
                                });
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        interstitialAd = null;
                        loadAd();
                    }
                });
    }

    private void showInterstitial() {
        if (interstitialAd != null) {
            interstitialAd.show(MainActivity.this);
        } else {
            loadAd();
        }
    }

    private void loadRewardedAd() {
        if (rewardedAd == null) {
            isLoading = true;
            AdRequest adRequest = new AdRequest.Builder().build();
            RewardedAd.load(
                    this,
                    getString(R.string.ad_rewarded_coins),
                    adRequest,
                    new RewardedAdLoadCallback() {
                        @Override
                        public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                            rewardedAd = null;
                            MainActivity.this.isLoading = false;
                            customToast("Please try again.", "");
                            alert1.dismiss();
                        }

                        @Override
                        public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                            MainActivity.this.isLoading = false;
                            MainActivity.this.rewardedAd = rewardedAd;
                            alert1.dismiss();
                            showRewardedVideo();
                        }
                    });
        }
    }

    private void showRewardedVideo() {
        if (rewardedAd == null) {
            return;
        }

        rewardedAd.setFullScreenContentCallback(
                new FullScreenContentCallback() {
                    @Override
                    public void onAdShowedFullScreenContent() {}

                    @Override
                    public void onAdFailedToShowFullScreenContent(AdError adError) {
                        rewardedAd = null;
                    }

                    @Override
                    public void onAdDismissedFullScreenContent() {
                        rewardedAd = null;
                        // alert1.dismiss();
                    }
                });
        Activity activityContext = MainActivity.this;
        rewardedAd.show(
                activityContext,
                new OnUserEarnedRewardListener() {

                    @Override
                    public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                        addTime();
                    }
                });
    }

    private void start() {

        SharedPreferences save_time = getSharedPreferences("time", Context.MODE_PRIVATE);
        long saved_ads_time = save_time.getLong("SAVED_TIME", Context.MODE_PRIVATE);

        if (saved_ads_time == Context.MODE_PRIVATE) {

            // Toast.makeText(SocksHttpMainActivity.this, "Less than 10mins! || Renew Access now",
            // Toast.LENGTH_LONG).show();

            long time = 1200;

            long millisInput = time * 1000;

            setTime(millisInput);
        }

        if (!mTimerRunning) {
            startTimer();
        }

        mConnected = true;
    }

    private void stop() {
        if (mTimerRunning) {
            pauseTimer();
        }

        mConnected = false;
    }

    private void addTime(long time) {

        setTime(time);

        if (mTimerRunning) {
            pauseTimer();
        }
        startTimer();
    }

    private void addTime() {
        long time = 7200;

        long millisInput = time * 1000;

        setTime(millisInput);

        if (mTimerRunning) {
            pauseTimer();
        }

        startTimer();
    }

    private void addRewardTime() {
        long added = coins.getLong("isAdded", 0);

        if (added == 1) {
            long added_time = coins.getLong("AddedTime", 0);

            if (mTimerRunning) {
                addTime(added_time);
            } else {
                setTime(added_time);
            }
            coins.edit().putLong("isAdded", 0).apply();
            retrieveTime();
        }
    }

    private void resetTimer() {

        SharedPreferences save_time = getSharedPreferences("time", Context.MODE_PRIVATE);

        mStartTimeInMillis = save_time.getLong("SAVED_TIME", Context.MODE_PRIVATE);

        mTimeLeftInMillis = mStartTimeInMillis;
        updateCountDownText();
    }

    private void pauseTimer() {
        mCountDownTimer.cancel();
        retrieveTime();
        mTimerRunning = false;
    }

    private void updateCountDownText() {
        final long days = TimeUnit.MILLISECONDS.toDays(mTimeLeftInMillis);
        final long hours =
                TimeUnit.MILLISECONDS.toHours(mTimeLeftInMillis)
                        - TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(mTimeLeftInMillis));
        final long minutes =
                TimeUnit.MILLISECONDS.toMinutes(mTimeLeftInMillis)
                        - TimeUnit.HOURS.toMinutes(
                                TimeUnit.MILLISECONDS.toHours(mTimeLeftInMillis));
        final long seconds =
                TimeUnit.MILLISECONDS.toSeconds(mTimeLeftInMillis)
                        - TimeUnit.MINUTES.toSeconds(
                                TimeUnit.MILLISECONDS.toMinutes(mTimeLeftInMillis));
        String formattedTime = String.format("%02d:%02d:%02d:%02d", days, hours, minutes, seconds);
        mTextViewCountDown.setText(formattedTime);
    }

    private void setTime(long milliseconds) {
        SharedPreferences save_time = getSharedPreferences("time", Context.MODE_PRIVATE);

        mStartTimeInMillis = milliseconds;

        SharedPreferences.Editor time_edit = save_time.edit();

        time_edit.putLong("SAVED_TIME", mTimeLeftInMillis + mStartTimeInMillis);

        time_edit.apply();

        resetTimer();
    }

    private void resumeTime() {

        resetTimer();

        // Use this code to continue time if app close accidentally while connected

        String state = SkStatus.getLastState();

        if (SkStatus.SSH_CONECTADO.equals(state)) {

            if (!mTimerRunning) {
                startTimer();
                mConnected = true;
            }
        }

        mTimerEnabled = true;
    }

    private void retrieveTime() {
        SharedPreferences saved_current_time = getSharedPreferences("time", Context.MODE_PRIVATE);

        SharedPreferences.Editor time_edit = saved_current_time.edit();

        time_edit.putLong("SAVED_TIME", mTimeLeftInMillis);

        time_edit.apply();
    }

    private void startTimer() {
        mEndTime = System.currentTimeMillis() + mTimeLeftInMillis;
        mCountDownTimer =
                new CountDownTimer(mTimeLeftInMillis, 1000) {

                    @Override
                    public void onTick(long millisUntilFinished) {
                        mTimeLeftInMillis = millisUntilFinished;
                        retrieveTime();
                        updateCountDownText();
                    }

                    @Override
                    public void onFinish() {
                        mTimerRunning = false;
                        pauseTimer();
                        resetTime();

                        // Code for auto stop vpn (sockshtttp)

                        Intent stopVPN = new Intent(SocksHttpService.TUNNEL_SSH_STOP_SERVICE);
                        LocalBroadcastManager.getInstance(MainActivity.this).sendBroadcast(stopVPN);

                        // no_time();

                    }
                }.start();
        mTimerRunning = true;
    }

    private void resetTime() {

        SharedPreferences save_time = getSharedPreferences("time", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = save_time.edit();

        editor.clear();
        editor.apply();
    }

    public void imao() {
        View inflate = LayoutInflater.from(this).inflate(R.layout.notif, null);
        MaterialAlertDialogBuilder alertDialogBuilder = new MaterialAlertDialogBuilder(this);
        alertDialogBuilder.setView(inflate);
        TextView title = inflate.findViewById(R.id.notiftext1);
        TextView ms = inflate.findViewById(R.id.confimsg);
        Button ok = inflate.findViewById(R.id.appButton1);
        title.setText("Release Notes!");
        ms.setText(this.config.geNote());
        ok.setText("Hide");
        final AlertDialog alert = alertDialogBuilder.create();
        alert.setCanceledOnTouchOutside(false);
        alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alert.getWindow().setGravity(Gravity.CENTER);
        ok.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View p1) {
                        alert.dismiss();
                        showInterstitial();
                    }
                });
        alert.show();
    }

    private void help() {

        LayoutInflater inflater =
                (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View inflate = inflater.inflate(R.layout.notif, (ViewGroup) null);
        AlertDialog.Builder builer = new AlertDialog.Builder(this);
        builer.setView(inflate);
        final TextView title = inflate.findViewById(R.id.notiftext1);
        TextView ms = inflate.findViewById(R.id.confimsg);
        final TextView bubu = inflate.findViewById(R.id.appButton1);
        TextView cancel = inflate.findViewById(R.id.appButton2);
        title.setText("Need Help?");
        ms.setText(
                "If you are connected to RSPH VPN but no internet data connection please go to settings and Enable DNS Forward then try  to connect\n\nIf Enabling DNS Forward still no internet data connection try again to Disable DNS Foward and start to connect");
        bubu.setText("Ok,Close");
        cancel.setText("Exit");
        final AlertDialog alert = builer.create();
        alert.setCanceledOnTouchOutside(false);
        alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alert.getWindow().setGravity(Gravity.CENTER);
        alert.show();
        bubu.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            alert.dismiss();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
        cancel.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alert.dismiss();
                    }
                });
        alert.show();
    }

    private void setSpinner() {
        SecurePreferences prefsPrivate = mConfig.getPrefsPrivate();
        serverSpinner.setSelection(prefsPrivate.getInt("LastSelectedServer", 0));
        payloadSpinner.setSelection(prefsPrivate.getInt("LastSelectedPayload", 0));
    }

    private void saveSpinner() {
        edit = mConfig.getPrefsPrivate().edit();
        int selectedItemPosition = this.serverSpinner.getSelectedItemPosition();
        int selectedItemPosition2 = this.payloadSpinner.getSelectedItemPosition();
        putInt = edit.putInt("LastSelectedServer", selectedItemPosition);
        putInt = edit.putInt("LastSelectedPayload", selectedItemPosition2);
        edit.apply();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!mTimerEnabled) {
            resumeTime();
        }
        addRewardTime();
        imgFavorite.setChecked(prefs.getBoolean("SavedSetup", false));
        setupSpinner.setSelection(prefs.getInt("SavedPos", 0));
        if (imgFavorite.isChecked()) {
            int i = setupSpinner.getSelectedItemPosition();
            if (i == 5) {
                if (prefs.getBoolean("isMsg", false)) {
                    messLay.setVisibility(View.VISIBLE);
                    tvMess.setText(prefs.getString("Mess", ""));
                }
            }
        }
        saveSpinner();
        setSpinner();
        mAppUpdateManager
                .getAppUpdateInfo()
                .addOnSuccessListener(
                        result -> {
                            if (result.updateAvailability()
                                    == UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS) {
                                try {
                                    mAppUpdateManager.startUpdateFlowForResult(
                                            result,
                                            AppUpdateType.IMMEDIATE,
                                            MainActivity.this,
                                            RC_APP_UPDATE);

                                } catch (IntentSender.SendIntentException ignored) {
                                }
                            }
                        });

        SkStatus.addStateListener(this);
        new Timer()
                .schedule(
                        new TimerTask() {
                            @Override
                            public void run() {
                                runOnUiThread(
                                        () -> {
                                            // updateHeaderCallback();
                                            // TODO: Implement this method
                                        });
                                // TODO: Implement this method
                            }
                        },
                        0,
                        1000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        dosavedata();
        SkStatus.removeStateListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTimerEnabled = true;
        if (mCountDownTimer != null) {
            mCountDownTimer.start();
            mCountDownTimer = null;
        }
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mActivityReceiver);
    }

    private void onShowe() {
        LayoutInflater inflater =
                (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View inflate = inflater.inflate(R.layout.update_dialog, null);
        final MaterialAlertDialogBuilder builer =
                new MaterialAlertDialogBuilder(this, R.style.MaterialAlertDialogText);
        builer.setView(inflate);
        TextView title = inflate.findViewById(R.id.notiftext1);
        TextView ms = inflate.findViewById(R.id.confimsg);

        title.setText("Facebook Page.");
        ms.setText(
                Html.fromHtml(
                        "</strong> "
                                + "For more upcoming updates kindly follow our facebook page, thank you."));

        builer.setPositiveButton(
                "Follow",
                (p1, p2) -> {
                    try {
                        startActivity(
                                new Intent(
                                        "android.intent.action.VIEW",
                                        Uri.parse("https://www.facebook.com/OfficialRSPHVPN")));
                        builer.create().dismiss();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
        builer.setNeutralButton("Later", (p1, p2) -> builer.create().dismiss());
        builer.create().show();
    }

    // Ads Setup Start Here

    public class MyAdapter extends PagerAdapter {

        private final List<String> titles;

        public MyAdapter(List<String> str) {
            titles = str;
        }

        @Override
        public int getCount() {
            // TODO: Implement this method
            return 2;
        }

        @Override
        public boolean isViewFromObject(View p1, Object p2) {
            // TODO: Implement this method
            return p1 == p2;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            int[] ids = new int[] {R.id.tab1, R.id.tab2, R.id.tab3};
            int id = 0;
            id = ids[position];
            // TODO: Implement this method
            return findViewById(id);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            // TODO: Implement this method
            return titles.get(position);
        }
    }
}
