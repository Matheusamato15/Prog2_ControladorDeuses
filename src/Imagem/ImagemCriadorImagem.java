package Imagem;

import javax.swing.ImageIcon;

public class ImagemCriadorImagem {
	public ImageIcon imagemFundo(String path, String description) {
        java.net.URL URLImagem = getClass().getClassLoader().getResource(path);
        if (URLImagem != null) {
            return new ImageIcon(URLImagem, description);
        } else {
            System.err.println("Não encontrei o arquivo: " + path);
            return null;
        }
    }
	
}
