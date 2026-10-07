package thanhdnh.ueh.edu.article_app;

import android.app.Activity;
import android.content.Context;
import android.widget.GridView;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UserData {
  public static UserList data;
  private Context context;
  private GridView gridview;
  private final ExecutorService executor = Executors.newSingleThreadExecutor();

  public UserData(Context context, GridView gridview) {
    this.context = context;
    this.gridview = gridview;
  }

  public static User getUserFromId(int id) {
    for (int i = 0; i < data.getUsers().size(); i++)
      if (data.getUsers().get(i).getId() == id)
        return data.getUsers().get(i);
    return null;
  }

  public void loadData(String url, Activity activity){
      executor.execute(()->{
          File file = Downloader.downloadFile(url, context.getCacheDir());
          String json = (file != null) ? readText(file) : readAsset("users.json");
          if(json != null)
            activity.runOnUiThread(()->{
              Gson gson = new Gson();
              data = gson.fromJson(json, (Type) UserList.class);
              UserAdapter adapter = new UserAdapter(data.getUsers(), context);
              gridview.setAdapter(adapter);
            });
        });
  }

  public String readText(File file){
    try {
      return readStream(new FileInputStream(file));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  public String readAsset(String name){
    try {
      return readStream(context.getAssets().open(name));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  private String readStream(InputStream stream) throws IOException {
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8))) {
      StringBuilder buffer = new StringBuilder();
      String line;
      while ((line = reader.readLine()) != null) {
        buffer.append(line).append("\n");
      }
      return buffer.toString();
    }
  }
}
