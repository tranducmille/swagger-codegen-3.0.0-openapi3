package io.swagger.codegen.v3.service;

import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.util.Json;
import io.swagger.util.Yaml;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.parser.OpenAPIV3Parser;
import io.swagger.v3.parser.core.models.ParseOptions;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class GeneratorServiceTest {

    @Test(description = "test generator service with html2")
    public void testGeneratorService_HTML2_Bearer() throws IOException {

        String path = getTmpFolder().getAbsolutePath();
        GenerationRequest request = new GenerationRequest();
        request
                .codegenVersion(GenerationRequest.CodegenVersion.V3)
                .type(GenerationRequest.Type.CLIENT)
                .lang("html2")
                .spec(loadSpecAsNode("3_0_0/html2BearerAuthIssue.yaml", true, false))
                .options(
                        new Options()
                                .outputDir(path)
                );
        List<File> files = new GeneratorService().generationRequest(request).generate();
        Assert.assertFalse(files.isEmpty());
        for (File f: files) {
            String relPath = f.getAbsolutePath().substring(path.length());
            if ("/index.html".equals(relPath)) {
                //api key
                Assert.assertTrue(FileUtils.readFileToString(f).contains("curl -X GET\\\n" +
                        "-H \"api_key: [[apiKey]]\"\\"));
                //basic
                Assert.assertTrue(FileUtils.readFileToString(f).contains("curl -X POST\\\n" +
                        " -H \"Authorization: Basic [[basicHash]]\"\\"));
                //bearer
                Assert.assertTrue(FileUtils.readFileToString(f).contains("curl -X PUT\\\n" +
                        " -H \"Authorization: Bearer [[accessToken]]\"\\"));
            }
        }

    }

    @Test(description = "test generator service with html2")
    public void testGeneratorService_HTML2() throws IOException {

        String path = getTmpFolder().getAbsolutePath();
        GenerationRequest request = new GenerationRequest();
        request
                .codegenVersion(GenerationRequest.CodegenVersion.V3)
                .type(GenerationRequest.Type.CLIENT)
                .lang("html2")
                .spec(loadSpecAsNode("3_0_0/swos196.yaml", true, false))
                .options(
                        new Options()
                                .outputDir(path)
                );

        List<File> files = new GeneratorService().generationRequest(request).generate();
        Assert.assertFalse(files.isEmpty());
    }

    @Test(description = "test generator service with java")
    public void testGeneratorService_notNullJacksonAnnotationJava_True() throws IOException {

        String path = getTmpFolder().getAbsolutePath();
        GenerationRequest request = new GenerationRequest();
        request
                .codegenVersion(GenerationRequest.CodegenVersion.V3)
                .type(GenerationRequest.Type.CLIENT)
                .lang("java")
                .spec(loadSpecAsNode("3_0_0/issue-9203.yaml", true, false))
                .options(
                        new Options()
                                .outputDir(path)
                                .addAdditionalProperty("notNullJacksonAnnotation", true)
                );

        List<File> files = new GeneratorService().generationRequest(request).generate();
        Assert.assertFalse(files.isEmpty());
        for (File f: files) {
            String relPath = f.getAbsolutePath().substring(path.length());
            if ("/src/main/java/io/swagger/client/model/OrderLineAudit.java".equals(relPath)) {
                Assert.assertTrue(FileUtils.readFileToString(f).contains("@JsonInclude(JsonInclude.Include.NON_NULL)"));
                Assert.assertTrue(FileUtils.readFileToString(f).contains("import com.fasterxml.jackson.annotation.JsonInclude"));
            }
        }
    }

    @Test(description = "test generator service with java")
    public void testGeneratorService_notNullJacksonAnnotationJava_False() throws IOException {

        String path = getTmpFolder().getAbsolutePath();
        GenerationRequest request = new GenerationRequest();
        request
                .codegenVersion(GenerationRequest.CodegenVersion.V3)
                .type(GenerationRequest.Type.CLIENT)
                .lang("java")
                .spec(loadSpecAsNode("3_0_0/issue-9203.yaml", true, false))
                .options(
                        new Options()
                                .outputDir(path)
                                .addAdditionalProperty("notNullJacksonAnnotation", false)
                );

        List<File> files = new GeneratorService().generationRequest(request).generate();
        Assert.assertFalse(files.isEmpty());
        for (File f: files) {
            String relPath = f.getAbsolutePath().substring(path.length());
            if ("/src/main/java/io/swagger/client/model/OrderLineAudit.java".equals(relPath)) {
                Assert.assertFalse(FileUtils.readFileToString(f).contains("@JsonInclude(JsonInclude.Include.NON_NULL)"));
                Assert.assertFalse(FileUtils.readFileToString(f).contains("import com.fasterxml.jackson.annotation.JsonInclude"));
            }
        }
    }


    @Test(description = "test generator service with spring")
    public void testGeneratorService_notNullJacksonAnnotationSpring_True() throws IOException {

        String path = getTmpFolder().getAbsolutePath();
        GenerationRequest request = new GenerationRequest();
        request
                .codegenVersion(GenerationRequest.CodegenVersion.V3)
                .type(GenerationRequest.Type.CLIENT)
                .lang("spring")
                .spec(loadSpecAsNode("3_0_0/issue-9203.yaml", true, false))
                .options(
                        new Options()
                                .outputDir(path)
                                .addAdditionalProperty("notNullJacksonAnnotation", true)
                );

        List<File> files = new GeneratorService().generationRequest(request).generate();
        Assert.assertFalse(files.isEmpty());
        for (File f: files) {
            String relPath = f.getAbsolutePath().substring(path.length());
            if ("/src/main/java/io/swagger/client/model/OrderLineAudit.java".equals(relPath)) {
                Assert.assertTrue(FileUtils.readFileToString(f).contains("@JsonInclude(JsonInclude.Include.NON_NULL)"));
                Assert.assertTrue(FileUtils.readFileToString(f).contains("import com.fasterxml.jackson.annotation.JsonInclude"));
            }
        }
    }

    @Test(description = "test generator service with spring")
    public void testGeneratorService_notNullJacksonAnnotationSpring_False() throws IOException {

        String path = getTmpFolder().getAbsolutePath();
        GenerationRequest request = new GenerationRequest();
        request
                .codegenVersion(GenerationRequest.CodegenVersion.V3)
                .type(GenerationRequest.Type.CLIENT)
                .lang("spring")
                .spec(loadSpecAsNode("3_0_0/issue-9203.yaml", true, false))
                .options(
                        new Options()
                                .outputDir(path)
                                .addAdditionalProperty("notNullJacksonAnnotation", false)
                );

        List<File> files = new GeneratorService().generationRequest(request).generate();
        Assert.assertFalse(files.isEmpty());
        for (File f: files) {
            String relPath = f.getAbsolutePath().substring(path.length());
            if ("/src/main/java/io/swagger/client/model/OrderLineAudit.java".equals(relPath)) {
                Assert.assertFalse(FileUtils.readFileToString(f).contains("@JsonInclude(JsonInclude.Include.NON_NULL)"));
                Assert.assertFalse(FileUtils.readFileToString(f).contains("import com.fasterxml.jackson.annotation.JsonInclude"));
            }
        }
    }

    @Test(description = "test generator oneOf ComposedSchema Properties")
    public void testGenerator_FlattenInlineComposedSchema() throws IOException {

        String path = getTmpFolder().getAbsolutePath();
        GenerationRequest request = new GenerationRequest();
        request
                .codegenVersion(GenerationRequest.CodegenVersion.V3)
                .type(GenerationRequest.Type.CLIENT)
                .lang("java")
                .spec(loadSpecAsNode("3_0_0/FlattenComposedInlineSchema.yaml", true, false))
                .options(
                        new Options()
                                .flattenInlineComposedSchema(true)
                                .outputDir(path)
                );

        List<File> files = new GeneratorService().generationRequest(request).generate();
        Assert.assertFalse(files.isEmpty());
        for (File f: files) {
            String relPath = f.getAbsolutePath().substring(path.length());
            if ("/src/main/java/io/swagger/client/model/ContactbasemodelAllOf1.java".equals(relPath)) {
                Assert.assertTrue("/src/main/java/io/swagger/client/model/ContactbasemodelAllOf1.java".equals(relPath));
            }
            if ("/src/main/java/io/swagger/client/model/TestOneOf2.java".equals(relPath)) {
                Assert.assertTrue("/src/main/java/io/swagger/client/model/TestOneOf2.java".equals(relPath));
            }
        }

    }

    @Test(description = "test generator oneOf ComposedSchema Properties")
    public void testGenerator_OneOf_ComposedSchemaProperties() throws IOException {

        String path = getTmpFolder().getAbsolutePath();
        GenerationRequest request = new GenerationRequest();
        request
                .codegenVersion(GenerationRequest.CodegenVersion.V3)
                .type(GenerationRequest.Type.CLIENT)
                .lang("html")
                .spec(loadSpecAsNode("3_0_0/OneOfPropertiesIssue.yaml", true, false))
                .options(
                        new Options()
                                .outputDir(path)
                );

        List<File> files = new GeneratorService().generationRequest(request).generate();
        Assert.assertFalse(files.isEmpty());
        for (File f: files) {
            String relPath = f.getAbsolutePath().substring(path.length());
            if ("/index.html".equals(relPath)) {
                //val_Member unique property
                Assert.assertTrue(FileUtils.readFileToString(f).contains("val_unique_reference"));
                //val_Member_Product unique property
                Assert.assertTrue(FileUtils.readFileToString(f).contains("val_property_1"));
            }
        }

    }

    @Test(description = "test generator service with jaxrs-cxf-client")
    public void testGeneratorService_Jaxrs_cxf_client() throws IOException {

        String path = getTmpFolder().getAbsolutePath();
        GenerationRequest request = new GenerationRequest();
        request
                .codegenVersion(GenerationRequest.CodegenVersion.V3)
                .type(GenerationRequest.Type.CLIENT)
                .lang("jaxrs-cxf-client")
                .spec(loadSpecAsNode("3_0_0/issue-9203.yaml", true, false))
                .options(
                        new Options()
                                .outputDir(path)
                );

        List<File> files = new GeneratorService().generationRequest(request).generate();
        Assert.assertFalse(files.isEmpty());
        for (File f: files) {
            String relPath = f.getAbsolutePath().substring(path.length());
            if ("/src/gen/java/io/swagger/model/OrderLineAudit.java".equals(relPath)) {
                Assert.assertFalse(FileUtils.readFileToString(f).contains("isisProcessed"));
            }
        }

    }

    @Test(description = "test generator service with jaxrs-cxf-cdi")
    public void testGeneratorService_Jaxrs_cxf_cdi() throws IOException {

        String path = getTmpFolder().getAbsolutePath();
        GenerationRequest request = new GenerationRequest();
        request
                .codegenVersion(GenerationRequest.CodegenVersion.V3)
                .type(GenerationRequest.Type.CLIENT)
                .lang("jaxrs-cxf-cdi")
                .spec(loadSpecAsNode("3_0_0/issue-9203.yaml", true, false))
                .options(
                        new Options()
                                .outputDir(path)
                );

        List<File> files = new GeneratorService().generationRequest(request).generate();
        Assert.assertFalse(files.isEmpty());
        for (File f: files) {
            String relPath = f.getAbsolutePath().substring(path.length());
            if ("/src/gen/java/io/swagger/model/OrderLineAudit.java".equals(relPath)) {
                Assert.assertFalse(FileUtils.readFileToString(f).contains("isisProcessed"));
            }
        }

    }

    @Test(description = "test generator service with jaxrs-resteasy-eap")
    public void testGeneratorService_Jaxrs_resteasy_eap() throws IOException {

        String path = getTmpFolder().getAbsolutePath();
        GenerationRequest request = new GenerationRequest();
        request
                .codegenVersion(GenerationRequest.CodegenVersion.V3)
                .type(GenerationRequest.Type.CLIENT)
                .lang("jaxrs-resteasy-eap")
                .spec(loadSpecAsNode("3_0_0/issue-9203.yaml", true, false))
                .options(
                        new Options()
                                .outputDir(path)
                );

        List<File> files = new GeneratorService().generationRequest(request).generate();
        Assert.assertFalse(files.isEmpty());
        for (File f: files) {
            String relPath = f.getAbsolutePath().substring(path.length());
            if ("/src/gen/java/io/swagger/model/OrderLineAudit.java".equals(relPath)) {
                Assert.assertFalse(FileUtils.readFileToString(f).contains("isisProcessed"));
            }
        }
    }

    @Test(description = "test generator service with jaxrs-resteasy")
    public void testGeneratorService_Jaxrs_resteasy() throws IOException {

        String path = getTmpFolder().getAbsolutePath();
        GenerationRequest request = new GenerationRequest();
        request
                .codegenVersion(GenerationRequest.CodegenVersion.V3)
                .type(GenerationRequest.Type.CLIENT)
                .lang("jaxrs-resteasy")
                .spec(loadSpecAsNode("3_0_0/issue-9203.yaml", true, false))
                .options(
                        new Options()
                                .outputDir(path)
                );

        List<File> files = new GeneratorService().generationRequest(request).generate();
        Assert.assertFalse(files.isEmpty());
        for (File f: files) {
            String relPath = f.getAbsolutePath().substring(path.length());
            if ("/src/gen/java/io/swagger/model/OrderLineAudit.java".equals(relPath)) {
                Assert.assertFalse(FileUtils.readFileToString(f).contains("isisProcessed"));
            }
        }
    }

    @Test(description = "test generator service with jaxrs-spec")
    public void testGeneratorService_Jaxrs_spec() throws IOException {

        String path = getTmpFolder().getAbsolutePath();
        GenerationRequest request = new GenerationRequest();
        request
                .codegenVersion(GenerationRequest.CodegenVersion.V3)
                .type(GenerationRequest.Type.CLIENT)
                .lang("jaxrs-spec")
                .spec(loadSpecAsNode("3_0_0/issue-9203.yaml", true, false))
                .options(
                        new Options()
                                .outputDir(path)
                );

        List<File> files = new GeneratorService().generationRequest(request).generate();
        Assert.assertFalse(files.isEmpty());
        for (File f: files) {
            String relPath = f.getAbsolutePath().substring(path.length());
            if ("/src/gen/java/io/swagger/model/OrderLineAudit.java".equals(relPath)) {
                Assert.assertFalse(FileUtils.readFileToString(f).contains("isisProcessed"));
            }
        }
    }

    @Test(description = "test generator service with spring")
    public void testGeneratorService_JavaSpring() throws IOException {

        String path = getTmpFolder().getAbsolutePath();
        GenerationRequest request = new GenerationRequest();
        request
                .codegenVersion(GenerationRequest.CodegenVersion.V3)
                .type(GenerationRequest.Type.CLIENT)
                .lang("spring")
                .spec(loadSpecAsNode("3_0_0/issue-9203.yaml", true, false))
                .options(
                        new Options()
                                .outputDir(path)
                );

        List<File> files = new GeneratorService().generationRequest(request).generate();
        Assert.assertFalse(files.isEmpty());
        for (File f: files) {
            String relPath = f.getAbsolutePath().substring(path.length());
            if ("/src/gen/java/io/swagger/model/OrderLineAudit.java".equals(relPath)) {
                Assert.assertFalse(FileUtils.readFileToString(f).contains("isisProcessed"));
            }
        }
    }

    @Test(description = "test generator service with spring")
    public void testGeneratorService_JavaSpringPetStore() throws IOException {

        String path = getTmpFolder().getAbsolutePath();
        GenerationRequest request = new GenerationRequest();
        request
                .codegenVersion(GenerationRequest.CodegenVersion.V3)
                .type(GenerationRequest.Type.SERVER)
                .lang("spring")
                .spec(loadSpecAsNode("2_0/petstore.yaml", true, true))
                .options(
                        new Options()
                                .outputDir(path)
                );

        List<File> files = new GeneratorService().generationRequest(request).generate();
        Assert.assertFalse(files.isEmpty());
        for (File f: files) {
            String relPath = f.getAbsolutePath().substring(path.length());
            if ("/src/main/java/io/swagger/model/Order.java".equals(relPath)) {
                Assert.assertTrue(FileUtils.readFileToString(f).contains("isComplete"));
            }
        }
    }

    @Test(description = "test generator service with java 3.0")
    public void testGeneratorServiceJava3() {

        GenerationRequest request = new GenerationRequest();
        request
                .codegenVersion(GenerationRequest.CodegenVersion.V3)
                .type(GenerationRequest.Type.SERVER)
                .lang("jaxrs-jersey")
                .spec(loadSpecAsNode("3_0_0/petstore.json", false, false))
                .options(
                        new Options()
                                .outputDir(getTmpFolder().getAbsolutePath())
                );
        List<File> files = new GeneratorService().generationRequest(request).generate();
        Assert.assertFalse(files.isEmpty());
    }

    @Test
    public void testNoModel() throws Exception{

        String path = getTmpFolder().getAbsolutePath();
        GenerationRequest request = new GenerationRequest();
        request
                .codegenVersion(GenerationRequest.CodegenVersion.V3)
                .type(GenerationRequest.Type.SERVER)
                .lang("jaxrs-jersey")
                .spec(loadSpecAsNode("3_0_0/noModel.yaml", true, false))
                .options(
                        new Options()
                                .outputDir(path)
                );
        List<File> files = new GeneratorService().generationRequest(request).generate();
        System.out.println(path);
        Assert.assertFalse(files.isEmpty());
        for (File f: files) {
            String relPath = f.getAbsolutePath().substring(path.length());
            if ("/src/main/java/io/swagger/api/impl/FooApiServiceImpl.java".equals(relPath) ||
                    "/src/gen/java/io/swagger/api/FooApiService.java".equals(relPath) ||
                    "/src/gen/java/io/swagger/api/FooApi.java".equals(relPath)) {
                Assert.assertFalse(FileUtils.readFileToString(f).contains("import io.swagger.model"));
            }
        }

    }

    @Test(description = "test generator service with java 2.0")
    public void testGeneratorServiceJava2() {

        GenerationRequest request = new GenerationRequest();
        request
                .codegenVersion(GenerationRequest.CodegenVersion.V2)
                .type(GenerationRequest.Type.SERVER)
                .lang("jaxrs")
                .spec(loadSpecAsNode("2_0/petstore.json", false, true))
                .options(
                        new Options()
                                .outputDir(getTmpFolder().getAbsolutePath())
                );
        List<File> files = new GeneratorService().generationRequest(request).generate();
        Assert.assertFalse(files.isEmpty());
    }

    @Test(description = "test generator service with java 3.0 and spec 2.0")
    public void testGeneratorServiceJava3Spec2() {

        GenerationRequest request = new GenerationRequest();
        request
                .codegenVersion(GenerationRequest.CodegenVersion.V3)
                .type(GenerationRequest.Type.SERVER)
                .lang("jaxrs-jersey")
                .spec(loadSpecAsNode("2_0/petstore.json", false, true))
                .options(
                        new Options()
                                .outputDir(getTmpFolder().getAbsolutePath())
                );
        List<File> files = new GeneratorService().generationRequest(request).generate();
        Assert.assertFalse(files.isEmpty());
    }

    @Test(description = "test generator service with java 3.0, spec as ref to file")
    public void testGeneratorServiceJava3RefSpec() {
        GenerationRequest request = new GenerationRequest();
        request
                .codegenVersion(GenerationRequest.CodegenVersion.V3)
                .type(GenerationRequest.Type.SERVER)
                .lang("jaxrs-jersey")
                .specURL("3_0_0/petstore.json")
                .options(
                        new Options()
                                .outputDir(getTmpFolder().getAbsolutePath())
                );
        List<File> files = new GeneratorService().generationRequest(request).generate();
        Assert.assertFalse(files.isEmpty());
    }


    @Test(description = "test generator service with java client 3.0")
    public void testGeneratorServiceJavaClient3() {

        GenerationRequest request = new GenerationRequest();
        request
                .codegenVersion(GenerationRequest.CodegenVersion.V3)
                .type(GenerationRequest.Type.CLIENT)
                .lang("java")
                .spec(loadSpecAsNode("3_0_0/petstore.json", false, false))
                .options(
                        new Options()
                                .outputDir(getTmpFolder().getAbsolutePath())
                                .artifactId("swagger-petstore-jersey2")
                                .library("jersey2")
                                .addAdditionalProperty("useRuntimeException", true)
                                .addAdditionalProperty("useRxJava", true)
                );
        List<File> files = new GeneratorService().generationRequest(request).generate();
        Assert.assertFalse(files.isEmpty());
    }

    @Test(description = "test boolean additional properties")
    public void testGeneratorServiceBooleanAdditionalProperties() throws Exception {

        String path = getTmpFolder().getAbsolutePath();
        GenerationRequest request = new GenerationRequest();
        request
                .codegenVersion(GenerationRequest.CodegenVersion.V3)
                .type(GenerationRequest.Type.CLIENT)
                .lang("java")
                .spec(loadSpecAsNode("3_0_0/swos92.yaml", true, false))
                .options(
                        new Options()
                            .outputDir(path)
                            .artifactId("swagger-petstore-jersey2")
                            .library("jersey2")
                            .addAdditionalProperty("useRuntimeException", true)
                            .addAdditionalProperty("useRxJava", true)
                );
        List<File> files = new GeneratorService().generationRequest(request).generate();
        Assert.assertFalse(files.isEmpty());
        for (File f: files) {
            String relPath = f.getAbsolutePath().substring(path.length());
            if ("/src/main/java/io/swagger/client/model/Product.java".equals(relPath)) {
                Assert.assertTrue(FileUtils.readFileToString(f).contains("Map<String, Object> foo = null"));
            }
        }

    }

    @Test(description = "test generator service with java client 2.0")
    public void testGeneratorServiceJavaClient2() {
        GenerationRequest request = new GenerationRequest();
        request
                .codegenVersion(GenerationRequest.CodegenVersion.V2)
                .type(GenerationRequest.Type.CLIENT)
                .lang("java")
                .spec(loadSpecAsNode("2_0/petstore.json", false, true))
                .options(
                        new Options()
                                .outputDir(getTmpFolder().getAbsolutePath())
                                .artifactId("swagger-petstore-jersey2")
                                .library("jersey2")
                );
        List<File> files = new GeneratorService().generationRequest(request).generate();
        Assert.assertFalse(files.isEmpty());
    }

    @Test(description = "test generator service resolved spec (openapi, openapi-yaml")
    public void testGeneratorService_ResolvedSpec() throws IOException {

        String path = getTmpFolder().getAbsolutePath();
        GenerationRequest request = new GenerationRequest();
        request
                .codegenVersion(GenerationRequest.CodegenVersion.V3)
                .type(GenerationRequest.Type.DOCUMENTATION)
                .lang("openapi")
                .spec(loadSpecAsNode("3_0_0/flattentest.yaml", true, false))
                .options(
                        new Options()
                                .outputDir(path)
                                .addAdditionalProperty("flattenSpec", false)
                );

        new GeneratorService().generationRequest(request).generate();
        String spec = FileUtils.readFileToString(new File(path + File.separator + "openapi.json"));
        Assert.assertFalse(spec.contains("#/components/schemas/inline_response_200"));
        Assert.assertFalse(spec.contains("#/components/schemas/body"));

        path = getTmpFolder().getAbsolutePath();
        request = new GenerationRequest();
        request
                .codegenVersion(GenerationRequest.CodegenVersion.V3)
                .type(GenerationRequest.Type.DOCUMENTATION)
                .lang("openapi-yaml")
                .spec(loadSpecAsNode("3_0_0/flattentest.yaml", true, false))
                .options(
                        new Options()
                                .outputDir(path)
                                .addAdditionalProperty("flattenSpec", "false")
                );


        new GeneratorService().generationRequest(request).generate();
        spec = FileUtils.readFileToString(new File(path + File.separator + "openapi.yaml"));
        Assert.assertFalse(spec.contains("#/components/schemas/inline_response_200"));
        Assert.assertFalse(spec.contains("#/components/schemas/body"));


        path = getTmpFolder().getAbsolutePath();
        request = new GenerationRequest();
        request
                .codegenVersion(GenerationRequest.CodegenVersion.V3)
                .type(GenerationRequest.Type.DOCUMENTATION)
                .lang("openapi")
                .spec(loadSpecAsNode("3_0_0/flattentest.yaml", true, false))
                .options(
                        new Options()
                                .outputDir(path)
                );

        new GeneratorService().generationRequest(request).generate();
        spec = FileUtils.readFileToString(new File(path + File.separator + "openapi.json"));
        Assert.assertTrue(spec.contains("#/components/schemas/inline_response_200"));
        Assert.assertTrue(spec.contains("#/components/schemas/body"));


        path = getTmpFolder().getAbsolutePath();
        request = new GenerationRequest();
        request
                .codegenVersion(GenerationRequest.CodegenVersion.V3)
                .type(GenerationRequest.Type.DOCUMENTATION)
                .lang("openapi-yaml")
                .spec(loadSpecAsNode("3_0_0/flattentest.yaml", true, false))
                .options(
                        new Options()
                                .outputDir(path)
                );

        new GeneratorService().generationRequest(request).generate();
        spec = FileUtils.readFileToString(new File(path + File.separator + "openapi.yaml"));
        Assert.assertTrue(spec.contains("#/components/schemas/inline_response_200"));
        Assert.assertTrue(spec.contains("#/components/schemas/body"));
        
        path = getTmpFolder().getAbsolutePath();
        request = new GenerationRequest();
        request
                .codegenVersion(GenerationRequest.CodegenVersion.V3)
                .type(GenerationRequest.Type.DOCUMENTATION)
                .lang("openapi-yaml")
                .specURL("src/test/resources/3_0_0/resolvefullytest.yaml")
                .options(
                        new Options()
                                .outputDir(path)
                                .resolveFully(true)
                );


        new GeneratorService().generationRequest(request).generate();
        spec = FileUtils.readFileToString(new File(path + File.separator + "openapi.yaml"));
        Assert.assertTrue(spec.contains("additionalProperties: true"));

    }

    protected static File getTmpFolder() {
        try {
            File outputFolder = File.createTempFile("codegentest-", "-tmp");
            outputFolder.delete();
            outputFolder.mkdir();
            outputFolder.deleteOnExit();
            return outputFolder;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    protected JsonNode loadSpecAsNode(final String file, boolean yaml, boolean v2) {
        InputStream in = null;
        String s = "";
        try {
            in = getClass().getClassLoader().getResourceAsStream(file);
            if (yaml) {
                if (v2) {
                    return Yaml.mapper().readTree(in);
                } else {
                    return io.swagger.v3.core.util.Yaml.mapper().readTree(in);
                }
            }
            if (v2) {
                return Json.mapper().readTree(in);
            }
            return io.swagger.v3.core.util.Json.mapper().readTree(in);
        } catch (Exception e) {
            throw new RuntimeException("could not load file " + file);
        } finally {
            IOUtils.closeQuietly(in);
        }
    }

    protected OpenAPI deserializeOpenAPI(final String file, boolean yaml, boolean v2) {
        InputStream in = null;
        String s = "";
        try {
            in = getClass().getClassLoader().getResourceAsStream(file);
            if (yaml) {
                return io.swagger.v3.core.util.Yaml.mapper().readValue(in, OpenAPI.class);
            }
            return io.swagger.v3.core.util.Json.mapper().readValue(in, OpenAPI.class);
        } catch (Exception e) {
            throw new RuntimeException("could not load file " + file);
        } finally {
            IOUtils.closeQuietly(in);
        }
    }

    private static OpenAPI parseOpenAPI(String path) {
        final ParseOptions options = new ParseOptions();
        options.setFlatten(Boolean.TRUE);
        return new OpenAPIV3Parser()
                .readLocation(path, null, options)
                .getOpenAPI();
    }
}
