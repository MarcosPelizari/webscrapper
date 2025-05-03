# Web Scraping de Produtos - Desafio InfoSimples

## Descrição do Projeto
Este projeto é uma solução para o desafio de web scraping. O objetivo é extrair informações de um produto a partir de uma página HTML (`https://infosimples.com/vagas/desafio/commercia/product.html`) e gerar um arquivo JSON com os dados estruturados. O scraping é realizado utilizando a biblioteca **Jsoup** para parsing de HTML e **Jackson** para serialização do JSON.

### Funcionalidades
- Extrai informações de um produto, incluindo:
    - **Título** (`title`)
    - **Marca** (`brand`)
    - **Categorias** (`categories`)
    - **Descrição** (`description`)
    - **SKUs** (`skus`): Nome, preço atual, preço antigo, disponibilidade.
    - **Propriedades** (`properties`): Lista de propriedades do produto de duas tabelas.
    - **Avaliações** (`reviews`): Nome do usuário, data, pontuação (score), texto da avaliação.
    - **Média das Avaliações** (`avgReview`): Média das pontuações das avaliações.
    - **URL** (`url`): URL da página do produto.
- Gera um arquivo JSON (`products.json`) com os dados extraídos.
- Inclui validações para lidar com elementos ausentes ou mal formatados no HTML.

## Pré-requisitos
Para executar o projeto, você precisa ter o seguinte instalado:

- **Java 8 ou superior**
- **Maven**
- **Dependências**:
    - **Jsoup** (para scraping de HTML): `org.jsoup:jsoup:1.20.1`
    - **Jackson Databind** (para serialização JSON): `com.fasterxml.jackson.core:jackson-databind:2.19.0`

## Estrutura do Projeto

- **`Main.java`**: Classe principal que contém a lógica de scraping e geração do JSON.
- **Pacote `scrap`**:
    - `Produto.java`: Representa o produto com campos como `title`, `brand`, `skus`, etc.
    - `Skus.java`: Representa um SKU com `name`, `currentPrice`, `oldPrice`, e `available`.
    - `Properties.java`: Representa uma propriedade do produto com `label` e `value`.
    - `Reviews.java`: Representa uma avaliação com `name`, `date`, `score`, e `text`.
- **`products.json`**: Arquivo gerado com o resultado do scraping.

## Como Executar

1. **Clone ou Baixe o Projeto**:
    - Clone o repositório ou baixe os arquivos para sua máquina.

2. **Configure as Dependências**:
    - Se usar Maven, execute `mvn install` para baixar as dependências.
    - Se não usar Maven, adicione os arquivos JAR do Jsoup e Jackson ao classpath.

3. **Execute o Programa**:
    - Compile e execute a classe `Main.java`:
      ```bash
      javac -cp .:jsoup-1.17.2.jar:jackson-databind-2.17.2.jar Main.java
      java -cp .:jsoup-1.17.2.jar:jackson-databind-2.17.2.jar Main
      ```
    - O programa acessará a URL fornecida, extrairá os dados e gerará o arquivo `products.json`.

4. **Verifique o Resultado**:
    - Abra o arquivo `products.json` para ver os dados extraídos no formato JSON.

## Exemplo de Saída (`products.json`)

```json
[
    {
        "title": "Rubber Duck",
        "brand": "DuckBrand",
        "categories": ["Home", "Bathroom", "Toys"],
        "description": "A classic rubber duck for bath time fun.",
        "skus": [
            {
                "name": "Yellow Duck",
                "currentPrice": 19.99,
                "oldPrice": 24.99,
                "available": true
            }
        ],
        "properties": [
            {
                "label": "Color",
                "value": "Various"
            },
            {
                "label": "Material",
                "value": "Rubber"
            }
        ],
        "reviews": [
            {
                "name": "Louise Eliel",
                "date": "28/07/2021",
                "score": 3,
                "text": "Very good rubber ducks, however I think they are a bit too big for me."
            }
        ],
        "avgReview": 3.30,
        "url": "https://infosimples.com/vagas/desafio/commercia/product.html"
    }
]
