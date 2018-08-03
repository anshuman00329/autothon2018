package webservice

import groovy.json.JsonBuilder



JsonBuilder builder = new JsonBuilder();
    builder.posts{

        id '3'
        title 'vedtbt'
        author 'svbfed','vrfesg'
    }

new File('data/file.json').write(builder.toPrettyString())


