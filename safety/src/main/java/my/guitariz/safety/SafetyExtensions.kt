package my.guitariz.safety

import android.app.Activity
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.support.v4.app.Fragment
import android.view.View

val ctxHandler by lazy { Handler(Looper.getMainLooper()) }

inline fun Activity.safety(crossinline block: () -> Unit) {
    if (isDestroyed) return

    runOnUiThread {
        if (!isDestroyed) block.invoke()
    }
}

inline fun Context.safety(crossinline block: () -> Unit) {
    (this as? Activity)?.let { self ->
        self.safety(block)
        return
    }

    if (Looper.getMainLooper().thread == Thread.currentThread())
        block()
    else
        ctxHandler.post { block() }
}

inline fun Fragment.safety(crossinline block: () -> Unit) = context?.safety(block)
inline fun View.safety(crossinline block: () -> Unit) = context?.safety(block)



