package com.movie.chang.revealsearchview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * Read Me
 * 布局：
 * 要有一个ToolBar配合，RevealSearchView和ToolBar重叠
 * 代码：
 * OnQueryTextListener可以回调 查询关键字和关键字变化
 * showSearch 打开搜索界面
 * hideSearch 关闭搜索界面
 */
public class MainActivity extends AppCompatActivity {

    private RevealSearchView searchView;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar= (Toolbar) findViewById(R.id.main_toolbar_tb);
        setSupportActionBar(toolbar);

        searchView = (RevealSearchView) findViewById(R.id.main_search_view_rsv);
        searchView.setOnQueryTextListener(new RevealSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String queryText) {
                Toast.makeText(MainActivity.this, "你搜索了" + queryText, Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {
        // 这里控制如果SearchView打开了，按返回键先关掉SearchView
        if (searchView.isSearchOpen()) {
            searchView.hideSearch();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                searchView.showSearch();

                return false;
            }
        });
        return true;
    }


}
