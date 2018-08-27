package my.guitariz.safetyexample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import my.guitariz.safety.safety

class ExampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example)

        safety {
            print("oops")
        }
    }
}
