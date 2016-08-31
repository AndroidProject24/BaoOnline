package com.toan_itc.baoonline.ui.addnews;

/**
 * Created by Toan.IT
 * Date: 25/05/2016
 */
/*

public class AddDataFragment extends BaseFragment {
    @BindView(R.id.spinner)
    AppCompatSpinner mSpinner;
    @BindView(R.id.edit_title)
    EditText mEditTitle;
    @BindView(R.id.edit_link)
    EditText mEditLink;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.txt_id)
    TextView txt_id;
    @BindView(R.id.txt_title)
    TextView txt_title;
    String item;
    private RecyclerViewAdapter rcAdapter;
    private Context mContext;
    private RealmManager mRealmManager;
    public AddDataFragment() {
        // Requires empty public constructor
    }

    public static AddDataFragment newInstance() {
        return new AddDataFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.add_news_fragment;
    }

    @Override
    protected void injectDependencies() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected StateLayout getLoadingTargetView() {
        return null;
    }

    @OnClick(R.id.btn_add)
    public void btn_add(){
        Toast.makeText(mContext,mEditTitle.getText().toString().trim()+"mEditLink="+mEditLink.getText().toString().trim(), Toast.LENGTH_LONG).show();
        mRealmManager.Set_Data(mEditTitle.getText().toString().trim(),mEditLink.getText().toString().trim());
        new Handler().postDelayed(new Runnable() {
                                      @Override
                                      public void run() {
                                          News news = mRealmManager.getnews(item);
                                         // Logger.e(news.getData().toString());
                                          if(news!=null) {
                                              txt_id.setText(String.valueOf(news.getId()));
                                              txt_title.setText(news.getTitle());
                                              //rcAdapter = new RecyclerViewAdapter(news.getData());
                                              recyclerview.setAdapter(rcAdapter);
                                          }
                                      }
                                  },3000);

    }
    @Override
    protected void initViews() {
        mRealmManager=new RealmManager(mContext);
        List<String> languages = new ArrayList<String>();
        languages.add("TinHot");
        languages.add("VnExpress");
        languages.add("Dân trí");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item, languages);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(dataAdapter);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                item = parent.getItemAtPosition(position).toString();
                mRealmManager.Set_News(item);
                Toast.makeText(parent.getContext(), "Android Simple Spinner Example Output..." + item, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        GridLayoutManager lLayout = new GridLayoutManager(mContext, 2);
        recyclerview.setHasFixedSize(true);
        recyclerview.setLayoutManager(lLayout);

    }
}
*/
