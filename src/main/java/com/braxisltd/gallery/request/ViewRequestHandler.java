package com.braxisltd.gallery.request;

import com.braxisltd.gallery.Domain.Category;
import com.braxisltd.gallery.application.ApplicationConfig;
import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.simpleframework.http.Response;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.Map;

import static com.braxisltd.gallery.request.stuff.Utils.directoryFilter;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;
import static java.util.Arrays.asList;

public abstract class ViewRequestHandler implements RequestHandler {

    private final ApplicationConfig config;

    protected ViewRequestHandler(ApplicationConfig config) {
        this.config = config;
    }

    protected View view(String template) throws IOException {
        return new View(template);
    }

    protected List<Category> loadCategories() {
        File imageRoot = new File(config.getDirectoryRoot());
        return newArrayList(Iterables.transform(asList(imageRoot.listFiles(directoryFilter())), new Function<File, Category>() {
            @Override
            public Category apply(File input) {
                return Category.fromKey(input.getName());
            }
        }));
    }

    protected ApplicationConfig config() {
        return config;
    }

    protected class View {
        
        private final Map<String, Object> models = newHashMap();
        private final Template template;

        private View(String templateFile) throws IOException {
            Configuration configuration = new Configuration();
            configuration.setClassForTemplateLoading(getClass(), "");
            configuration.setObjectWrapper(new DefaultObjectWrapper());
            template = configuration.getTemplate(templateFile);
        }

        protected View withModel(String key, Object model) {
            models.put(key, model);
            return this;
        }

        protected void render(Response response) throws IOException, TemplateException {
            setHeaders(response);
            OutputStreamWriter body = new OutputStreamWriter(response.getOutputStream());
            template.process(models, body);
            body.close();
        }

        private void setHeaders(Response response) {
            long time = System.currentTimeMillis();
            response.set("Content-Type", "text/html");
            response.set("Server", "SimpleHelloWorld/1.0 (Simple 4.0)");
            response.setDate("Date", time);
            response.setDate("Last-Modified", time);
        }

    }
}
