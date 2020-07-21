package com.example.cameratest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Camera;
import android.graphics.ImageFormat;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.Image;
import android.media.ImageReader;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.Size;
import android.util.SparseIntArray;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private  static  final SparseIntArray ORIENTATIONS = new SparseIntArray();
    static {
        ORIENTATIONS.append(Surface.ROTATION_0,90);
        ORIENTATIONS.append(Surface.ROTATION_90,0);
        ORIENTATIONS.append(Surface.ROTATION_180,270);
        ORIENTATIONS.append(Surface.ROTATION_270,180);
    }

    //定义界面上的根布局管理器
    private FrameLayout rootLayout;
    //定义自定义的AutoFitTextureView 的组件，用于预览摄像头照片。
    private AutoFitTextureView textureView;
    //摄像头ID
    private String mCameraId = "0";//0为后置摄像头，1为前置摄像头。
    //定义代表摄像头的成员变量
    private CameraDevice cameraDevice;
    //预览的尺寸；
    private Size previewSize;
    private CaptureRequest.Builder previewRequestBuilder;
    //定义用于预览照片的捕获请求；
    private CaptureRequest previewRequest;
    //定义CameraCaptureSession的成员变量。
    private CameraCaptureSession cameraCaptureSession;
    private ImageReader imageReader;

    AutoFitTextureView t1;
    private final TextureView.SurfaceTextureListener mSurfaceTextureListener = new TextureView.SurfaceTextureListener() {
        @Override
        public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
            openCamera(width,height);
        }

        @Override
        public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
            configureTransform(width,height);
        }

        @Override
        public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
            return true;
        }

        @Override
        public void onSurfaceTextureUpdated(SurfaceTexture surface) {

        }
    };
    private final CameraDevice.StateCallback stateCallback = new CameraDevice.StateCallback() {
        @Override
        //摄像头被打开是激发该方法。
        public void onOpened(CameraDevice camera) {
            MainActivity.this.cameraDevice = camera;
            //开始预览
            createCameraPreviewSession();
        }
        //摄像头断开连接时激发该方法。
        @Override
        public void onDisconnected(CameraDevice camera) {
            cameraDevice.close();
            MainActivity.this.cameraDevice = null;
        }
        //错误时激发该方法.
        @Override
        public void onError(CameraDevice camera, int error) {
            cameraDevice.close();
            MainActivity.this.cameraDevice = null;
            MainActivity.this.finish();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rootLayout = findViewById(R.id.root);
        Log.d("chh","主界面完成");
        requestPermissions(new String[]{Manifest.permission.CAMERA},0x123);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 0x123 && grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

            t1 = findViewById(R.id.auto1);

            //创建预览摄像头照片的TextureView组件
            textureView = new AutoFitTextureView(MainActivity.this,null);
            //设置监听器
            textureView.setSurfaceTextureListener(mSurfaceTextureListener);
            rootLayout.addView(textureView);
            findViewById(R.id.capture).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    captureStillPicture();
                    /*Button btn1 = new Button(MainActivity.this);
                    btn1.setText("hehe");
                    rootLayout.addView(btn1);*/
                    Log.d("chh","添加了拍照按钮监听器");
                }
            });
        }
    }
    private void captureStillPicture(){
        try {
            if(cameraDevice == null){
                return ;
            }
            //创建作为拍照的CaptureRequest.Builder。
            CaptureRequest.Builder captureRequestBuilder = cameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_STILL_CAPTURE);
            //将imageReader的surface作为 captureRequestBuilder的目标。
            captureRequestBuilder.addTarget(imageReader.getSurface());
            //设置成自动对焦模式。
            captureRequestBuilder.set(CaptureRequest.CONTROL_AE_MODE,CaptureRequest.CONTROL_AF_MODE_CONTINUOUS_PICTURE);
            //设置自动曝光模式。
            captureRequestBuilder.set(CaptureRequest.CONTROL_AE_MODE,CaptureRequest.CONTROL_AE_MODE_ON_AUTO_FLASH);
            //获取设备方向
            int roration = getWindowManager().getDefaultDisplay().getRotation();
            //根据设备方向计算设置照片的方向
            captureRequestBuilder.set(CaptureRequest.JPEG_ORIENTATION,ORIENTATIONS.get(roration));
            //停止连续取景
            cameraCaptureSession.stopRepeating();
            //捕获静态图像
            cameraCaptureSession.capture(captureRequestBuilder.build(), new CameraCaptureSession.CaptureCallback() {
                @Override
                public void onCaptureCompleted(CameraCaptureSession session, CaptureRequest request, TotalCaptureResult result) {
                    super.onCaptureCompleted(session, request, result);
                    try {
                        //拍照完成时激发该方法。
                        previewRequestBuilder.set(CaptureRequest.CONTROL_AF_TRIGGER,CaptureRequest.CONTROL_AF_TRIGGER_CANCEL);
                        //设置自动曝光模式。
                        previewRequestBuilder.set(CaptureRequest.CONTROL_AE_MODE,CaptureRequest.CONTROL_AE_MODE_ON_AUTO_FLASH);
                        //打开连续取景
                        cameraCaptureSession.setRepeatingRequest(previewRequest,null,null);
                    }catch (CameraAccessException e){
                        e.printStackTrace();
                    }
                }
            },null);
        }catch (CameraAccessException e){
            e.printStackTrace();
        }
    }
    //根据手机旋转的方向确定预览图片的方向
    private void configureTransform(int viewWidth,int viewHeight){
        if(null == previewRequest){
            return ;
        }
        //获取手机旋转方向
        int rotation = getWindowManager().getDefaultDisplay().getRotation();
        Matrix matrix = new Matrix();
        RectF viewRect = new RectF(0,0,viewWidth,viewHeight);
        RectF bufferRect = new RectF(0,0,previewSize.getHeight(),previewSize.getWidth());
        float centerX = viewRect.centerX();
        float centerY = viewRect.centerY();
        //处理手机横屏的的情况
        if (Surface.ROTATION_90 == rotation ||Surface.ROTATION_270 == rotation){
            bufferRect.offset(centerX-bufferRect.centerX(),centerY - bufferRect.centerY());
        }
        //处理手机倒置的情况
        else if(Surface.ROTATION_180 == rotation){
            matrix.postRotate(180,centerX,centerY);
        }
        textureView.setTransform(matrix);
    }
    //打开摄像头
    private void openCamera(int width,int height){
        setUpCameraOutputs(width,height);
        configureTransform(width,height);
        CameraManager manager = (CameraManager)getSystemService(Context.CAMERA_SERVICE);
        try {
            //如果用户没有授予使用摄像头，则直接返回
            if(checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
                return ;
            }
            manager.openCamera(mCameraId,stateCallback,null);
        }catch (CameraAccessException e){
            e.printStackTrace();
        }
    }

    private void createCameraPreviewSession(){
        try {
            SurfaceTexture texture = textureView.getSurfaceTexture();
            texture.setDefaultBufferSize(previewSize.getWidth(),previewSize.getHeight());
            Surface surface = new Surface(texture);
            //创建作为预览的CaptureRequest.Builder
            previewRequestBuilder = cameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
            //将textureView的surface作为CaptureRequest.Builder的目标
            previewRequestBuilder.addTarget(new Surface(texture));
            //创建CameraCaptureSession,该对象负责管理处理预览请求和拍照请求
            cameraDevice.createCaptureSession(Arrays.asList(surface, imageReader.getSurface()), new CameraCaptureSession.StateCallback() {
                @Override
                public void onConfigured(CameraCaptureSession session) {
                    //当摄像头为空的时候
                    if(null == cameraDevice){
                        return;
                    }
                    //当摄像头已经准备好时，开始显示预览。
                     cameraCaptureSession = session;
                    // 设置自动对焦
                    previewRequestBuilder.set(CaptureRequest.CONTROL_AF_MODE,CaptureRequest.CONTROL_AE_MODE_ON_AUTO_FLASH);
                    //开始相机预览
                    previewRequest = previewRequestBuilder.build();
                    try {
                        //设置预览时连续捕捉图像数据。
                        cameraCaptureSession.setRepeatingRequest(previewRequest,null,null);

                    }catch (CameraAccessException e){
                        e.printStackTrace();
                    }
                }

                @Override
                public void onConfigureFailed(CameraCaptureSession session) {
                    Toast.makeText(MainActivity.this,"完蛋了，等死吧！！",Toast.LENGTH_LONG).show();
                }
            },null);
        }catch (CameraAccessException e){
            e.printStackTrace();
        }
    }
    private void setUpCameraOutputs(int width,int height){
        CameraManager manager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try {
            //获取指定摄像头特性
            CameraCharacteristics characteristics = manager.getCameraCharacteristics(mCameraId);
            StreamConfigurationMap map = characteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
            //获取摄像头支持的最大尺寸
            Size largest = Collections.max(Arrays.asList(map.getOutputSizes(ImageFormat.JPEG)),new CompareSizesByArea());
            //创建一个ImageReader对象，用于获取摄像头的图像数据。
            imageReader = ImageReader.newInstance(largest.getWidth(),largest.getHeight(),ImageFormat.JPEG,2);
            imageReader.setOnImageAvailableListener(new ImageReader.OnImageAvailableListener() {
                @Override
                public void onImageAvailable(ImageReader reader) {
                    Image image = reader.acquireLatestImage();
                    ByteBuffer buffer = image.getPlanes()[0].getBuffer();
                    byte [] bytes= new byte[buffer.remaining()];
                    //使用IO流将照片写入指定文件。
                    File file = new File(getExternalFilesDir(null),"pic.jpg")
                            ;
                    buffer.get(bytes);
                    try (FileOutputStream output = new FileOutputStream(file);){
                        output.write(bytes);
                        Toast.makeText(MainActivity.this,"保存:"+ file,Toast.LENGTH_LONG).show();

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    finally {
                        image.close();
                    }
                }
            },null);
            //获取最佳的预览尺寸
            previewSize = chooseOptimalSize(map.getOutputSizes(SurfaceTexture.class),width,height,largest);
            //根据选中的预览尺寸来调整预览组件的长宽比。
            int orientation = getResources().getConfiguration().orientation;
            if(orientation == Configuration.ORIENTATION_LANDSCAPE){
                textureView.setAspectRatio(previewSize.getWidth(),previewSize.getHeight());
            }
            else
            {
                textureView.setAspectRatio(previewSize.getHeight(),previewSize.getWidth());
            }
        }catch (CameraAccessException e){
            e.printStackTrace();
        }catch (NullPointerException e){
            System.out.println("出现错误。");
        }
    }
    private static Size chooseOptimalSize(Size[] choices,int width,int height,Size aspectRatio){
        List<Size> bigEnough = new ArrayList<>();
        int w = aspectRatio.getWidth();
        int h = aspectRatio.getHeight();
        for(Size option: choices){
            if(option.getHeight() == option.getWidth()* h / w && option.getWidth() >= width && option.getHeight() >= height){
                bigEnough.add(option);
            }
        }
        //如果找到多个预览尺寸，获取其中面积最小的
        if(bigEnough.size() > 0){
            return Collections.min(bigEnough,new CompareSizesByArea());
        }else
        {
            System.out.println("找不到合适的预览尺寸");
            return choices[0];
        }
    }
    //为Size 定义一个比较器Caomparator
    static  class CompareSizesByArea implements Comparator<Size>{
        @Override
                public int compare(Size lhs,Size rhs){
            return Long.signum((long)lhs.getWidth()*lhs.getHeight() - (long)rhs.getWidth()*rhs.getHeight());
        }
    }

}
