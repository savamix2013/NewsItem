package util

import com.example.newsappkmp.di.KoinDIFactory
import com.example.newsappkmp.di.resolve
import java.lang.ref.WeakReference
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

// Лістинг 7.36: Реалізація делегованого властивості для спрощеного підключення ViewModel
class ViewModels<R : Any, T : Any>(private val clazz: KClass<*>) : ReadOnlyProperty<R, T?> {
    private var weakReference: WeakReference<T>? = null

    @Suppress("UNCHECKED_CAST")
    override fun getValue(thisRef: R, property: KProperty<*>): T? {
        if (weakReference?.get() == null) {
            val instance = KoinDIFactory.resolve(clazz) as? T
            weakReference = WeakReference(instance)
        }
        return weakReference?.get()
    }
}

inline fun <reified T : Any> viewModels() = ViewModels<Any, T>(T::class)