package com.example.adopet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

public class AddMyPetActivity extends AppCompatActivity {

    private  static final int CAMERA_REQUEST_CODE=100;
    private  static final int STORAGE_REQUEST_CODE=200;

    private  static final int IMAGE_PICK_CAMERA_CODE=300;
    private  static final int IMAGE_PICK_GALLERY_CODE=400;

    String[] cameraPermissions;
    String[] storagePermissions;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference ref;
    FirebaseUser user;
    FirebaseAuth mFAuth;
    Context context = this;
    Button CreateButton;
    ImageView imageIv;
    EditText PetName, PetType, PetOwnerName, PetAge, OtherFeatures, PetGender;
    ProgressDialog pd;
    String name, mail, password, userid, dp;

    Uri image_rui = null;

    String username;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_my_pet);

        cameraPermissions = new String[] {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        storagePermissions = new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE};


        mFAuth = FirebaseAuth.getInstance();
        checkUserStatus();

        ref = FirebaseDatabase.getInstance().getReference("Users");
        Query query = ref.orderByChild("email").equalTo(mail);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot ds: snapshot.getChildren()){
                    name = "" + ds.child("name").getValue();
                    mail = "" + ds.child("email").getValue();
                    dp = "" + ds.child("image").getValue();

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



//        petimage = (ImageView) findViewById(R.id.imgofpet);
//        petimage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                petimage.setImageResource(R.drawable.pet9);
//            }
//        });
        PetOwnerName = (EditText) findViewById(R.id.txt_petowner_name);
        PetName = (EditText) findViewById(R.id.txt_name);
        PetType = (EditText) findViewById(R.id.txt_type);
        PetAge = (EditText) findViewById(R.id.txt_age);
        PetGender= (EditText) findViewById(R.id.txt_gender);
        OtherFeatures = (EditText) findViewById(R.id.txt_features);
        imageIv = findViewById(R.id.imgofpet);
        pd = new ProgressDialog(this);
        CreateButton = (Button) findViewById(R.id.btn_create);

        //get image from camera/gallery
        imageIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImagePickDialog();

            }
        });

        CreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String petname = PetName.getText().toString();
                String type = PetType.getText().toString();
                String age = PetAge.getText().toString();
                String pet_owner_name  = PetOwnerName.getText().toString();
                String features = OtherFeatures.getText().toString();

                if(TextUtils.isEmpty(petname)){
                    Toast.makeText(AddMyPetActivity.this, "Enter Pet Name", Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(pet_owner_name)){
                    Toast.makeText(AddMyPetActivity.this, "Enter Pet Owner Name", Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(type)){
                    Toast.makeText(AddMyPetActivity.this, "Enter Pet Type", Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(age)){
                    Toast.makeText(AddMyPetActivity.this, "Enter Pet Age", Toast.LENGTH_SHORT).show();
                }
                if(image_rui==null){
                    uploadData(petname, pet_owner_name, type, age, features, "noImage");

                }else{
                    uploadData(petname, pet_owner_name, type, age, features, String.valueOf(image_rui));

                }


            }
        });



    }

    private void uploadData(final String petname, final String pet_owner_name, final String type, final String age, final String features, String uri) {
    pd.setMessage("Creating pet's profile..");
    pd.show();
    final String timeStamp = String.valueOf(System.currentTimeMillis());

    String filePathAndName = "Posts/" + "post_" + timeStamp;

    if(!uri.equals("noImage")){
        //post with image
        StorageReference ref = FirebaseStorage.getInstance().getReference().child(filePathAndName);
        ref.putFile(Uri.parse(uri)).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask.isSuccessful());

                String dowlandUri = uriTask.getResult().toString();

                if(uriTask.isSuccessful()){

                    HashMap<Object,String> hashMap = new HashMap<>();
                    hashMap.put("uid", userid);
                    hashMap.put("username",name);
                    hashMap.put("password",password);
                    hashMap.put("mail",mail);
                    hashMap.put("uDp", dp);
                    hashMap.put("pId",timeStamp);
                    hashMap.put("pPetOwnerName",pet_owner_name);
                    hashMap.put("pPetName", petname);
                    hashMap.put("pPetAge",age);
                    hashMap.put("pPetType",type);
                    hashMap.put("pOtherFeatures",features);
                    hashMap.put("pImage",dowlandUri);
                    hashMap.put("pTime",timeStamp);



                    //path the store user data named "Posts"
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Posts");
                    //put data within hashmap in ref
                    ref.child(timeStamp).setValue(hashMap)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    //adding pet p. in database
                                    pd.dismiss();
                                    Toast.makeText(AddMyPetActivity.this, "Pet Profile published", Toast.LENGTH_SHORT).show();
                                    //reset views
                                    PetOwnerName.setText("");
                                    PetName.setText("");
                                    PetAge.setText("");
                                    PetType.setText("");
                                    PetGender.setText("");
                                    OtherFeatures.setText("");
                                    imageIv.setImageURI(null);
                                    image_rui = null;
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                     //failed adding pet p. in database
                                    pd.dismiss();
                                    Toast.makeText(AddMyPetActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                }

            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        pd.dismiss();

                    }
                });
    }
    else{
        //post without image

        HashMap<Object,String> hashMap = new HashMap<>();
        hashMap.put("uid", userid);
        hashMap.put("username",name);
        hashMap.put("password",password);
        hashMap.put("mail",mail);
        hashMap.put("uDp", dp);
        hashMap.put("pId",timeStamp);
        hashMap.put("pPetOwnerName",pet_owner_name);
        hashMap.put("pPetName", petname);
        hashMap.put("pPetAge",age);
        hashMap.put("pPetType",type);
        hashMap.put("pOtherFeatures",features);
        hashMap.put("pImage","noImage");
        hashMap.put("pTime",timeStamp);



        //path the store user data named "Posts"
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Posts");
        //put data within hashmap in ref
        ref.child(timeStamp).setValue(hashMap)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        //adding pet p. in database
                        pd.dismiss();
                        Toast.makeText(AddMyPetActivity.this, "Pet Profile published", Toast.LENGTH_SHORT).show();
                        PetOwnerName.setText("");
                        PetName.setText("");
                        PetAge.setText("");
                        PetType.setText("");
                        PetGender.setText("");
                        OtherFeatures.setText("");
                        imageIv.setImageURI(null);
                        image_rui = null;

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //failed adding pet p. in database
                        pd.dismiss();
                        Toast.makeText(AddMyPetActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }
    }

    private void showImagePickDialog() {
        //options camera or gallery to show in dialog
        String[] options = {"Camera", "Gallery"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose Image from");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which==0){
                    //camera click
                    if(!checkCameraPermission()) {
                        requestCameraPermission();
                    }else{
                        pickFromCamera();
                    }
                }

                if(which==1){
                    //gallery click
                    if(!checkStoragePermission()) {
                        requestStoragePermission();
                    }else{
                        pickFromGallery();
                    }
                }

            }
        });
        builder.create().show();
    }

    private void pickFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_GALLERY_CODE);
    }

    private void pickFromCamera() {
        ContentValues cv = new ContentValues();
        cv.put(MediaStore.Images.Media.TITLE,"Temp Pick");
        cv.put(MediaStore.Images.Media.DESCRIPTION,"Temp Descr");
        image_rui = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, cv);

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, image_rui);
        startActivityForResult(intent, IMAGE_PICK_CAMERA_CODE);
    }

    private boolean checkStoragePermission(){

        boolean result = ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)== (PackageManager.PERMISSION_GRANTED);
        return result;
    }

    private void  requestStoragePermission() {
        ActivityCompat.requestPermissions(this, storagePermissions, STORAGE_REQUEST_CODE);
    }

    private boolean checkCameraPermission(){

        boolean result = ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)== (PackageManager.PERMISSION_GRANTED);
        boolean result1 = ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)== (PackageManager.PERMISSION_GRANTED);
        return result && result1;
    }

    private void  requestCameraPermission() {
        ActivityCompat.requestPermissions(this, cameraPermissions, CAMERA_REQUEST_CODE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        checkUserStatus();
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkUserStatus();
    }

    private void checkUserStatus() {
        FirebaseUser user = mFAuth.getCurrentUser();
        if(user != null){
            mail = user.getEmail();
            userid = user.getUid();

        }else{
            startActivity(new Intent(this, HomeActivity.class));
            finish();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case CAMERA_REQUEST_CODE:{
                if(grantResults.length>0) {
                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean storageAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    if(cameraAccepted && storageAccepted){
                        pickFromCamera();
                    }else{
                        Toast.makeText(this, "Camera & Storage both permissions necessary. ", Toast.LENGTH_SHORT).show();
                    }
                }else{
                }
            }
            break;
            case STORAGE_REQUEST_CODE:{
                if(grantResults.length>0) {
                    boolean storageAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if(storageAccepted){
                        pickFromGallery();
                    }else{
                        Toast.makeText(this, "Storage permission neccessary. ", Toast.LENGTH_SHORT).show();
                    }

                }else{

                }
            }
            break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode== RESULT_OK){

            if(requestCode==IMAGE_PICK_GALLERY_CODE){
                image_rui = data.getData();

                imageIv.setImageURI(image_rui);

            }
            else if(requestCode == IMAGE_PICK_CAMERA_CODE) {
                imageIv.setImageURI(image_rui);

            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}

