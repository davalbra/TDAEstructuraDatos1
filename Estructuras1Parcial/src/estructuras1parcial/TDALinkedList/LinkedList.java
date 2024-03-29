/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructuras1parcial.TDALinkedList;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author david
 */
public class LinkedList<E> {

    private Nodo<E> head;
    private Nodo<E> last;

    public boolean addFirst(E contenido) {
        Nodo<E> nuevo = new Nodo<>(contenido);
        if (isEmpty()) {
            head = nuevo;
            last = nuevo;
            return true;
        }

        nuevo.setNext(head);
        head = nuevo;
        return true;
    }

    public boolean addLast(E contenido) {
        Nodo<E> nuevo = new Nodo<>(contenido);
        if (isEmpty()) {
            head = nuevo;
            last = nuevo;
            return true;
        }

        last.setNext(nuevo);
        last = nuevo;
        return true;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        int contador = 0;
        for (Nodo<E> n = head; n != null; n = n.getNext()) {
            contador++;
        }
        return contador;
    }

    public boolean add(int index, E contenido) {
        Nodo<E> nodo = new Nodo<>(contenido);
        if (index > this.size()) {
            return false;
        }
        if (index == 0) {

            addFirst(contenido);

        }
        if (index == size()) {
            addLast(contenido);
        }
        int tmp = 0;
        for (Nodo<E> n = head; tmp < index - 1; n = n.getNext()) {
            if (tmp == index - 1) {
                n.setNext(nodo);
                nodo.setNext(n.getNext());
            }
            tmp++;
        }
        return true;
    }

    public boolean remove(int index) {
        if (index > size()) {
            return false;
        }
        if (index == 0) {
            removeFirst();
        }
        if (index == size()) {
            removeLast();
        }
        int tmp = 0;
        for (Nodo<E> n = head; tmp < index - 1; n = n.getNext()) {
            if (tmp == index - 1) {
                n.setNext(n.getNext().getNext());
                return true;
            }
        }
        return false;
    }

    public boolean removeFirst() {
        if (size() == 1) {
            head = null;
            last = null;
            return true;
        }
        head = head.getNext();
        return true;
    }

    public boolean removeLast() {
        int tmp = 0;
        int index = size();
        for (Nodo<E> n = head; tmp < index - 1; n = n.getNext()) {
            if (tmp == index - 1) {
                n.setNext(null);
                last = n;
                return true;
            }
        }
        return false;
    }

    public E get0() {
        return head.getContenido();
    }

    public E getN() {
        return last.getContenido();
    }

    public E get(int index) {
        if (index > size()) {
            return null;
        }
        int tmp = 0;
        for (Nodo<E> n = head; tmp < index; n = n.getNext()) {
            if (tmp == index) {
                return n.getContenido();
            }
        }
        return null;
    }

    //tema de Examen
    public Map<LinkedList<E>, Integer> contarDistintosEnSublistas(int k) {
        Map<LinkedList<E>, Integer> mapa = new HashMap<>();

        for (Nodo<E> n = head; n != null; n = n.getNext()) {
            if (cantidadRestante(n) >= k) {
                LinkedList<E> revisar = contenido(k, n);
                mapa.put(revisar, contarUnicos(revisar));
            }
        }
        return mapa;
    }

    public int contarUnicos(LinkedList<E> lista) {
        Set<E> set = new HashSet<>();
        for (Nodo<E> n = lista.getHead(); n != null; n = n.getNext()) {
            set.add(n.getContenido());
        }
        return set.size();
    }

    public LinkedList<E> contenido(int i, Nodo<E> nodo) {
        LinkedList<E> listaReturn = new LinkedList<>();
        for (Nodo<E> n = nodo; listaReturn.size() < i; n = n.getNext()) {
            listaReturn.addLast(n.getContenido());
        }
        return listaReturn;
    }

    public int cantidadRestante(Nodo<E> nodo) {
        int cantidad = 0;
        for (Nodo<E> n = nodo; n != null; n = n.getNext()) {
            cantidad++;
        }
        return cantidad;
    }
    //Fin de tema de Examen

    public void imprimir() {
        for (Nodo<E> n = head; n != null; n = n.getNext()) {
            System.out.print(" " + n.getContenido());
        }
        System.out.println("");
    }

    public Iterator<E> iterator() {
        Iterator<E> it = new Iterator<E>() {
            Nodo<E> n = head;

            @Override
            public boolean hasNext() {
                return n.getNext() != null;
            }

            @Override
            public E next() {
                return n.getContenido();
            }
        };
        return it;
    }

    public Nodo<E> getHead() {
        return head;
    }

    public void setHead(Nodo<E> head) {
        this.head = head;
    }

    public Nodo<E> getLast() {
        return last;
    }

    public void setLast(Nodo<E> last) {
        this.last = last;
    }

}
