/**
 *
 */
package Repository;

import com.intellij.codeInsight.CustomExceptionHandler;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.Arrays;

/**
 * @author Mike
 *
 */


public class MyArray<T> implements Iterable<T>
{
    private int _size;
    //protected int _cap;
    private T _objs[];


    public MyArray(int s)
    {
        _objs = (T[])new Object[s];
        _size = 0;
    }

    @Override
    public Iterator<T> iterator()
    {
        Iterator<T> it = new Iterator<T>()
        {
            private int currIndex = 0;

            @Override
            public boolean hasNext()
            {
                return currIndex < _size && _objs[currIndex] != null;
            }

            @Override
            public T next()
            {
                return _objs[currIndex++];
            }

            @Override
            public void remove()
            {
                throw new UnsupportedOperationException();
            }
        };
        return it;
    }

    private void resize()
    {
        /*
        Object aux[] = new Object[_cap * 2];
        for (Integer i = 0; i < _cap; ++i)
        {

        }*/
    }

    public void add(T obj)
    {

        _objs[_size++] = obj;
    }

    public T remove(int pos)
    {
        //if (pos >= _size)
        //    throw MyRepoException("Index not valid!");
        T el = _objs[pos];
        for (Integer i = pos; i < _size - 1; ++i)
        {
            _objs[i] = _objs[i + 1];
        }
        _size--;
        return el;
    }

    public void sort()
    {
        Arrays.sort(_objs);
    }

    public int getSize()
    {
        return _size;
    }
}
