<h3>RaphaAPI</h3>

<p>Sua utilidade é para os desenvolvedores poderem utilizar novas opções de eventos e bibliotecas facilmente, ainda está básica, porém pretendo melhorá-la cada vez mais.</p>

☛ <b>JitPack <i>(Maven)</i></b>

  Repositório:
```
<repository>
  <id>jitpack.io</id>
  <url>https://jitpack.io</url>
</repository>
```

  Dependência:
```
<dependency>
  <groupId>com.github.iDimaBR</groupId>
  <artifactId>RaphaAPI</artifactId>
  <version>VERSION</version>
</dependency>
```

➥ Eventos novos

- `ArmorChangeEvent` - Toda e qualquer tipo de armadura trocada pelo <i>inventário</i> ou equipado pela <i>mão</i> é chamado
- `EntityPreDeathEvent` - Toda entidade prestes a morrer é chamado
- `PlayerChangeChunkEvent` - Toda troca de chunk é chamado
- `PlayerJumpEvent` - Todo pulo de jogadores é chamado

➥ Utilidades

- `ConfigUtil` - Precisamente criará novos arquivos de configuração.
- `ItemBuilder` - Criador de item para facilitar manuseio.
- `MoneyUtil` - Utilidades na hora de trabalhar com dinheiro.

➥ SQLConnector

Para instanciar a conexão, temos dois meios:

# SQLITE

```java

private final SQLConnector connection = new SQLConnector("file");
```

# MySQL

```java

private final SQLConnector connection = new SQLConnector("127.0.0.1", "database", "root", "password");
```

# Métodos do executor

Primeiramente, precisamos chamar o executor com `connection.executor()` em uma variável.

Após isso, temos acesso aos metodos:

```java

private SQLExecutor executor = connection.executor();

executor.select("SELECT * FROM database WHERE name = ?", "joão");
executor.update("DELETE FROM database WHERE name = ?", "joão");
executor.update("INSERT INTO database (name) VALUES (?)", "joão");
executor.select(Object.class, "SELECT * FROM database WHERE name ?", "joão");
```
