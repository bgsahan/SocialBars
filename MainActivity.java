import //importing classes

public MainActivity extends Activity implements MainFragment.
    {
    @Override
    protected void onCreate(Bundle savedInstanceState)
        {
        super.onCreate(savedInstanceState)
        
        //TODO: Set Content activity main layout
        setContentView(R.Layout.activity_main);
        
        //TODO: Fragment transaction with MainFragment
        if(getFragmentManager().findFragmentById(android.R.Id.mainFragmentContainer)==null)
            {
            getFragmentManager().beginTransaction().add(android.R.Id.mainFragmentContainer, new MainFragment()).commit();
            }
        
        //TODO: Implementation of interface methods from MainFragment
        }
