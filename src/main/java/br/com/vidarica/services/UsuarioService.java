package br.com.vidarica.services;

import br.com.vidarica.dao.UsuarioDao;
import br.com.vidarica.exceptions.UserNotFoundException;
import br.com.vidarica.model.Usuario;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class UsuarioService {

    public static void cadastrarUsuario(String nome, String email, String senha) {
        try {
            Usuario user = new Usuario(nome, email, senha);
            UsuarioDao usuarioDao = new UsuarioDao();
            usuarioDao.cadastrarUsuario(user);

            Usuario usuarioCadastrado = usuarioDao.getUsuario("id", user.getId());

            if (usuarioCadastrado == null) {
                System.out.println("Usuário não encontrado.");
                return;
            }

            System.out.println("Usuário cadastrado com sucesso!");
            System.out.println("Nome: " + usuarioCadastrado.getNome());
            System.out.println("Email: " + usuarioCadastrado.getEmail());
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar usuário: " + e.getMessage());

        }
    }

    public static int listarUsuarios(int pageSize, int offset) {
        try {
            HashMap<String, Integer> response = new HashMap<>();
            UsuarioDao usuarioDao = new UsuarioDao();
            int total = usuarioDao.getTotalUsuarios();
            List<Usuario> usuarios = usuarioDao.getAllUsuarios(pageSize, offset);

            System.out.println("=== Lista de Usuários ===");
            for (Usuario usuario : usuarios) {
                System.out.println("ID: " + usuario.getId());
                System.out.println("Nome: " + usuario.getNome());
                System.out.println("Email: " + usuario.getEmail());
                System.out.println("-------------------------");

                System.out.println("Total de usuários: " + total);

            }
            usuarioDao.close();
            return total;
        } catch (Exception e) {
            System.out.println("Erro ao listar usuários: " + e.getMessage());
        }

        return 0;

    }

    public static Usuario consultarUsuarioPorEmail(String email) throws SQLException, UserNotFoundException {
        UsuarioDao usuarioDao = new UsuarioDao();
        Usuario usuario = usuarioDao.getUsuario("email", email);

        if (usuario != null) {
            System.out.println("=== Detalhes do Usuário ===");
            System.out.println("ID: " + usuario.getId());
            System.out.println("Nome: " + usuario.getNome());
            System.out.println("Email: " + usuario.getEmail());
            usuarioDao.close();
        } else {
            System.out.println("Usuário não encontrado.");
            throw new UserNotFoundException("Usuário não encontrado.");
        }

        return usuario;
    }
}
