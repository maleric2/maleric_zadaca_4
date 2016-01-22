/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maleric_zadaca_4.data.cache;

/**
 *
 * @author Marko
 */
public interface Cache<T> {
    public void release(T resource);
    public T acquire(int num);
}
