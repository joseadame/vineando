/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tempranillo.modelo;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import tempranillo.BD.GestionBD;
import tempranillo.entidades.Almacen;
import tempranillo.entidades.Bodega;
import tempranillo.entidades.Vino;
import tempranillo.entidades.WishList;


/**
 *
 * @author joseadamefernandez
 *
 * Esta clase se encarga de gestionar todo lo relativo a las bodegas.
 *
 */
public class GestionBodegas extends tempranillo.utils.SessionManagement {

    //Este metodo da de alta la bodega por defecto del usuario al registrarse.
    public int altaBodegaPorDefecto(Bodega bodega){
     int id = 0; //recuperaremos el con el que la bodega se ha dado de alta
        try {
            iniciarTransaccion();
            sesion.save(bodega);//guardamos el usuario en la tabla TUsuarios
            trx.commit();
            return bodega.getIdbodega();
        } catch (HibernateException he) {
            manejaExcepcion(he);
        } finally {
            sesion.close();
        }
        return 0;
   }

    public Bodega buscarBodega(int idbodega) {
        Bodega bodega = null;
        try {
            iniciarTransaccion();
            Query query = sesion.createQuery("FROM Bodega b WHERE b.idbodega=:cadena");
            query.setParameter("cadena", idbodega);
            trx.commit();
            return bodega = (Bodega) query.uniqueResult();
        } catch (HibernateException he) {
            manejaExcepcion(he);
        } finally {
            sesion.close();
        }
        return null;
    }


    public List<Bodega> listarBodegasUsuario(int idusuario){

    List<Bodega> listabodegas = null;
        try {
            actualizarNotaBodega(idusuario);
            iniciarTransaccion();

            Query query = sesion.createQuery("FROM Bodega b WHERE b.idusuario=:cadena");

            query.setParameter("cadena", idusuario);

            return listabodegas =  query.list();
        } catch (HibernateException e) {
            manejaExcepcion(e);
        }
        finally{
            cerrarSesion();}
        return null;
    }

    public List<Vino> listarVinosBodega(int idbodega) {

        List<Vino> listavinos;
        try {
            iniciarTransaccion();

           Query query = sesion.createQuery("SELECT DISTINCT v FROM Vino as v, Bodega b join b.listavinos v WHERE b.idbodega=:cadena");
            //Query query = sesion.createQuery("SELECT DISTINCT V FROM Vino v, Almacen a where")
            query.setParameter("cadena", idbodega);

            return listavinos =  query.list();
        } catch (HibernateException e) {
            manejaExcepcion(e);
        }
        finally {
            cerrarSesion();}
        return null;


    }

    public Integer altaBodega(Bodega bodega) {
        int id = 0; //recuperaremos el con el que la bodega se ha dado de alta

        try {
            if (!bodega.getDescripcion().equals(""))
            {
            iniciarTransaccion();
            sesion.save(bodega);//guardamos el vino.
            trx.commit();

            return bodega.getIdbodega();
            }
            else
            {
                throw new HibernateException("El nombre de la lista no puede estar vacio");

            }

        } catch (HibernateException he) {


            manejaExcepcion(he);

        } finally {
            sesion.close();


        }

        return 0;




    }
    
    
    
    //Recupera los listos almacenados en una determianda lista del usuario.
    public List<Vino> recuperarVinosLista(int idbodega){
        
        
         List<Vino> listavinos;
        try {
            iniciarTransaccion();

           // Query query = sesion.createSQLQuery("SELECT tvinos.idvino, tvinos.nombre, tvinos.tipovino, tvinos.anio, tvinos.variedad, tvinos.zona, tvinos.pais, tvinos.barcode, tvinos.idusuario, tvinos.notamedia, tvinos.numerovotaciones, tvinos.fechaalta, tvinos.bodega, tvinos.rutaimagen, tvinos.status, tvinos.comentariocata FROM public.almacenvinos, public.tvinos WHERE almacenvinos.idvino = tvinos.idvino and almacenvinos.idbodega=:cadena");
            Query query = sesion.createQuery("Select v  From Almacen a,Vino v where a.idvino=v.idvino and a.idbodega=:cadena");
            query.setParameter("cadena", idbodega);

            return listavinos =  query.list();
        } catch (HibernateException e) {
            manejaExcepcion(e);
        }
        finally {cerrarSesion();}
        return null;
    
    
    
    
    
    
    }


    public void altaVinoEnBodega (int idbodega, int idvino){

    try{

        iniciarTransaccion();
        //Bodega aBodega = (Bodega) sesion.load(Bodega.class, idbodega);
        //Vino aVino = (Vino) sesion.load(Vino.class, idvino);
        Almacen almaaux = new Almacen();
        
        almaaux.setIdbodega(idbodega);
        almaaux.setIdvino(idvino);
        
        sesion.save(almaaux);
        trx.commit();
        cerrarSesion();
       // aBodega.getListavinos().add(aVino);//añadimos el vino a la bodega
       
        //Set listavinos=aBodega.getListavinos();
       // List listavinos = recuperarVinosLista(aBodega.getIdbodega());
        iniciarTransaccion();
        Query query = sesion.createQuery("Select v  From Almacen a,Vino v where a.idvino=v.idvino and a.idbodega=:cadena");
            query.setParameter("cadena", idbodega);

          List listavinos =  query.list();
        
        Iterator<Vino> iterador = listavinos.iterator();
        
        
        Double sumanotas=0.0;
        int indice=0;
        while(iterador.hasNext())
        {
            Vino vino = iterador.next();
            sumanotas=sumanotas+vino.getNotamedia();
            indice++;


        }

        Double notamedia=sumanotas/indice; //calculamos la nota media de la bodega.
        DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
        simbolo.setDecimalSeparator('.');
        DecimalFormat formateador = new DecimalFormat("0.0",simbolo);
        String x = formateador.format(notamedia);
        Bodega aBodega = (Bodega) sesion.load(Bodega.class, idbodega);
        aBodega.setNotamedia(Double.parseDouble(x));

        trx.commit();

    }
    catch(HibernateException he){


    manejaExcepcion(he);

    }
    finally {
        cerrarSesion();}





    }
    
    
    private Almacen buscarAlmacen(int idbodega, int idvino){
    
    
    iniciarTransaccion();
    
    Query query = sesion.createQuery("FROM Almacen a where a.idbodega=:idbodega and a.idvino=:idvino");
    query.setParameter("idbodega", idbodega);
    query.setParameter("idvino",idvino);
    Almacen almacen = (Almacen) query.uniqueResult();
    
    
    
    
    cerrarSesion();
    
    
    return almacen;
    
    
    }
    
    


    public void borrarVinoDeBodega(int idbodega, int idvino){


     
       Almacen almacen= buscarAlmacen(idbodega, idvino);
        
       iniciarTransaccion();
        sesion.delete(almacen);

        trx.commit();
        
       cerrarSesion();


    }

    public void borrarBodega(int idbodega){


        iniciarTransaccion();
        Bodega aBodega = (Bodega) sesion.load(Bodega.class, idbodega);

        sesion.delete(aBodega);//eliminamos la bodega.

        trx.commit();

       cerrarSesion();




    }

    public void cambiarNombreBodega(int idbodega, String descripcion) {

        iniciarTransaccion();
        Bodega aBodega = (Bodega) sesion.load(Bodega.class, idbodega);

        aBodega.setDescripcion(descripcion);

        trx.commit();


        cerrarSesion();


    }

    private void actualizarNotaBodega(int idusuario) {

            iniciarTransaccion();

            Query query = sesion.createQuery("FROM Bodega b WHERE b.idusuario=:cadena");

            query.setParameter("cadena", idusuario);
            List bodegas = query.list();
            
            
            Iterator<Bodega> iterador = bodegas.iterator();
            
            while(iterador.hasNext())
            {
                Bodega bodega = iterador.next();
               // Set vinos = bodega.getListavinos();
               
                //List<Vino> vinos = recuperarVinosLista(bodega.getIdbodega());
                  query = sesion.createQuery("Select v  From Almacen a,Vino v where a.idvino=v.idvino and a.idbodega=:cadena");
                  query.setParameter("cadena", bodega.getIdbodega());

                List  listavinos =  query.list();
                int indice=0;
                Double sumanotas=0.0;
                Iterator<Vino> iterador2 = listavinos.iterator();
                while(iterador2.hasNext())
                {
                  Vino vinoaux= iterador2.next();
                  sumanotas=sumanotas+vinoaux.getNotamedia();
                  indice++;



                }

                Double notamedia=sumanotas/indice; //calculamos la nota media de la bodega.
                if(sumanotas!=0)
                {
                DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
                simbolo.setDecimalSeparator('.');
                DecimalFormat formateador = new DecimalFormat("0.0",simbolo);
                String x = formateador.format(notamedia);
                bodega.setNotamedia(Double.parseDouble(x));
                }
                else
                {
                    bodega.setNotamedia(0.0);

                }
            }
          trx.commit();
          cerrarSesion();
    }

    public boolean altaVinoEnWishList(int idusuario, int idvino) {
       

       if (!estaEnWishlist(idusuario,idvino)){
       WishList wish = new WishList();
       wish.setIdusuario(idusuario);
       wish.setIdvino(idvino);
       Date fechaactual = new Date();
       wish.setFecha(fechaactual);

       iniciarTransaccion();
       sesion.save(wish);
       trx.commit();
       cerrarSesion();
       return true;
       }
       else
       {
         return false;

       }



    }
    
    
    public void borrarVinoWishList(int idusuario,int idvino){
    
        WishList wish= buscarVinoWishList(idusuario, idvino);
        
       iniciarTransaccion();
        sesion.delete(wish);

        trx.commit();
        
       cerrarSesion();
    
    
    
    
    
    }
    
    
    
    public WishList buscarVinoWishList(int idusuario,int idvino){
    
    iniciarTransaccion();
    
    Query query = sesion.createQuery("FROM WishList w where w.idusuario=:idusuario and w.idvino=:idvino");
    query.setParameter("idusuario", idusuario);
    query.setParameter("idvino",idvino);
    WishList wish = (WishList) query.uniqueResult();
    
    
    
    
    cerrarSesion();
    
    
    return wish;
    
    
    
    
    }
    
    

    private boolean estaEnWishlist(int idusuario, int idvino) {
        iniciarTransaccion();
        Query query = sesion.createQuery("FROM WishList w WHERE w.idusuario = :idusu AND w.idvino=:idv ");
        query.setParameter("idusu", idusuario);
        query.setParameter("idv", idvino);

         WishList wish = (WishList) query.uniqueResult();
         cerrarSesion();
        if (wish==null)
            return false;
        else
            return true;



    }

    public Long numeroVecesEnWishlist(int idvino)
    {

        iniciarTransaccion();
        Query query = sesion.createQuery("SELECT COUNT(*) FROM WishList w WHERE w.idvino = :idv");
        query.setParameter("idv", idvino);
        Long numeroveces;
        
        
        numeroveces= (Long) query.uniqueResult();
        cerrarSesion();
        return numeroveces;






    }

    public Long numeroVinosEnWishList(int id) {
        iniciarTransaccion();
        Query query = sesion.createQuery("SELECT COUNT(*) FROM WishList w WHERE w.idusuario = :idusu");
        query.setParameter("idusu", id);
        Long numerovinos;
        numerovinos= (Long) query.uniqueResult();
        cerrarSesion();
        return numerovinos;
    }

    public List listarListasCompartidas(int idusuario) {
        List<Bodega> listabodegascompartidas;
        try {
            actualizarNotaBodega(idusuario);
            iniciarTransaccion();

            Query query = sesion.createQuery("FROM Bodega b WHERE b.idusuario=:cadena and b.compartida='Si'");

            query.setParameter("cadena", idusuario);

            return listabodegascompartidas =  query.list();
        } catch (HibernateException e) {
            manejaExcepcion(e);
        }
        finally{
            cerrarSesion();}
        return null;
    }




    }



