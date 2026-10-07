package thanhdnh.ueh.edu.article_app;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class ViewUserActivity extends AppCompatActivity {
  ImageView iv_detail;
  TextView tv_detail_uname, tv_detail_id, tv_detail_password, tv_detail_bio;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_view_user);
    getSupportActionBar().hide();

    iv_detail = findViewById(R.id.iv_detail);
    tv_detail_uname = findViewById(R.id.tv_detail_uname);
    tv_detail_id = findViewById(R.id.tv_detail_id);
    tv_detail_password = findViewById(R.id.tv_detail_password);
    tv_detail_bio = findViewById(R.id.tv_detail_bio);

    int id = (int) getIntent().getLongExtra("id", 0);
    User user = UserData.getUserFromId(id);
    if (user == null) {
      finish();
      return;
    }

    Picasso.get().load(user.getUrl_profile()).resize(400, 400).centerCrop().into(iv_detail);
    tv_detail_uname.setText(user.getUname());
    tv_detail_id.setText(getString(R.string.label_id, user.getId()));
    StringBuilder masked = new StringBuilder();
    for (int i = 0; i < user.getPassword().length(); i++)
      masked.append('*');
    tv_detail_password.setText(getString(R.string.label_password, masked.toString()));
    tv_detail_bio.setText(user.getShort_bio());
  }
}
