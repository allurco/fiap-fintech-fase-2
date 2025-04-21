package br.com.vidarica;

import br.com.vidarica.menu.Menu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Menu menu = new Menu();
        menu.exibirMenuPrincipal();
    }
}
