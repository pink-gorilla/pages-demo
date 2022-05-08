# pages-demo

Its easy to send visualizations to pages.

You need babashka installed.

## administer pages

You can work with either our public server on https://www.pinkgorilla.org
or you can start your own pages server.


```
bb users
bb my-pages
bb get-page
bb publish-pages
```

## for use with scratchpad (in goldly-docs)

Start goldly docs.

The devtools included will watch on localhost:8080/scratchpad for messages that are visualized.
Navigate in the browser to localhost:8080/devtools/scratchpad

```
bb show-random
```

This shows a random visualization in the browser.

