import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;
import scrap.Produto;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        String url = "https://infosimples.com/vagas/desafio/commercia/product.html";
        String userAgent = "Mozilla/5.0 (Windows NT 11.0; Win64;x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/134.0.6998.166 Safari/537.36";
        List<Produto> produtos = new ArrayList<>();
        Document doc;

        try {
            doc = Jsoup.connect(url)
                    .userAgent(userAgent)
                    .get();

        Elements produtosElementos = doc.select(".container");

        for (Element product: produtosElementos) {
            Produto produto = new Produto();

            //Extrair Título
            produto.setTitle(product.select("h2[id='product_title']").text().trim());

            //Extrais Marca
            produto.setBrand(product.select("div[class='brand']").text().trim());

            //Extrair Categorias
            Elements categoryElements = product.select("nav[aria-label=breadcrumbs]");
            String categorieText = categoryElements.text().trim();
            String[] categories = categorieText.split("\\s*>\\s*");

            produto.setCategories(categories);

            //Extrair Descrição
            Elements descriptionElements = product.select(".proddet p");
            StringBuilder descriptionText = new StringBuilder();
            for (Element description: descriptionElements)
                descriptionText.append(description.text().trim()).append(" ");
            produto.setDescription(descriptionText.toString().trim());

            produtos.add(produto);
        }

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        String jsonOutput = mapper.writeValueAsString(produtos);
        mapper.writeValue(new File("products.json"), produtos);
        System.out.println("JSON gerado: " + jsonOutput);
        System.out.println("Dados salvos");

        } catch (IOException e) {
            throw new RuntimeException(e);

        }

        System.out.println(produtos.toString());

    }
}
