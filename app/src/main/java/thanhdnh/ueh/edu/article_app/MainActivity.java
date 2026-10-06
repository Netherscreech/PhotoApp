package thanhdnh.ueh.edu.article_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
  // users.json nằm ở thư mục gốc repo (bản fork của Netherscreech)
  private static final String USERS_URL = "https://raw.githubusercontent.com/Netherscreech/PhotoApp/master/users.json";

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
