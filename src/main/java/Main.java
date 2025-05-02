import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;
import scrap.Produto;
import scrap.Properties;
import scrap.Skus;

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


            //Extrair produtos(skus)
            List<Skus> skus = new ArrayList<>();
            Elements skusElements = product.select(".skus-area .card");
            for (Element skuElement: skusElements) {
                Skus sku = new Skus();

                //Nome
                Element nameElement = skuElement.select("meta[itemprop='name']").first();
                sku.setName(nameElement != null ? nameElement.attr("content").trim() : "");

                //Preço Atual(Current Price)
                Element currentPriceElement = skuElement.select("div[class='prod-pnow']").first();
                Double currentPrice = null;
                if (currentPriceElement != null) {
                    String priceText = currentPriceElement.text()
                            .replace("R$", "")
                            .replace(",", ".")
                            .trim();
                    currentPrice =Double.parseDouble(priceText);
                }
                sku.setCurrentPrice(currentPrice != null ?  currentPrice : null);

                //Preço Antigo(Old Price)
                Element oldPriceElement = skuElement.select(".prod-pold").first();
                Double oldPrice = null;
                if (oldPriceElement != null) {
                    String priceText = oldPriceElement.text()
                            .replace("R$", "")
                            .replace(",", ".")
                            .trim();
                    oldPrice = Double.parseDouble(priceText);
                }
                sku.setOldPrice(oldPrice != null ? oldPrice : null);

                //Disponivel?(Available)
                Boolean available = !skuElement.select("i").text().contains("Out of stock");
                sku.setAvailable(available);

                skus.add(sku);
            }

            //Lista Propriedades(Properties)
            List<Properties> properties = new ArrayList<>();
            Element propertiesElement = product.select("table.pure-table.pure-table-bordered").first();
            if (propertiesElement != null) {
                Elements rows = propertiesElement.select("tbody tr");
                for (Element row : rows) {
                    Elements cells = row.select("td");
                    if (cells.size() >= 2) {
                        Properties propriedade = new Properties();
                        propriedade.setLabel(cells.get(0).text().trim());
                        propriedade.setValue(cells.get(1).text().trim());
                        properties.add(propriedade);
                    }
                }
            }

            //Segunda Lista de Propriedades
            Element secondTable = product.select("div[id='propadd']").first();
            if (secondTable != null) {
                Elements rows = secondTable.select("tbody tr");
                for (Element row: rows) {
                    Elements cells = row.select("td");
                    if (cells.size() >= 2) {
                        Properties propriedade = new Properties();
                        propriedade.setLabel(cells.get(0).text().trim());
                        propriedade.setValue(cells.get(1).text().trim());
                        properties.add(propriedade);
                    }
                }
            }

            produto.setProperties(properties);
            produto.setSkus(skus);

            if (!produto.getTitle().isEmpty()) {
                produtos.add(produto);
            }
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
