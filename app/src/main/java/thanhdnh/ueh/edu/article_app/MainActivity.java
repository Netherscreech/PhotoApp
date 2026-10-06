package thanhdnh.ueh.edu.article_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
  // Sau khi fork, thay YOUR_GITHUB_USERNAME bằng tài khoản GitHub của bạn (file users.json nằm ở thư mục gốc repo)
  private static final String USERS_URL = "https://raw.githubusercontent.com/YOUR_GITHUB_USERNAME/PhotoApp/master/users.json";

  public GridView gridview;

  private AdapterView.OnItemClickListener onitemclick = new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
      Intent intent = new Intent(getBaseContext(), ViewUserActivity.class);
      intent.putExtra("id", gridview.getAdapter().getItemId(position));
      startActivity(intent);
    }
  };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    getSupportActionBar().hide();

    gridview = findViewById(R.id.gridview);
    new UserData(getBaseContext(), gridview).loadData(USERS_URL, this);
    gridview.setOnItemClickListener(onitemclick);
  }

}
