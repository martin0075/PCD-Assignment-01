package org.gradle.accessors.dm;

import org.gradle.api.NonNullApi;
import org.gradle.api.artifacts.MinimalExternalModuleDependency;
import org.gradle.plugin.use.PluginDependency;
import org.gradle.api.artifacts.ExternalModuleDependencyBundle;
import org.gradle.api.artifacts.MutableVersionConstraint;
import org.gradle.api.provider.Provider;
import org.gradle.api.provider.ProviderFactory;
import org.gradle.api.internal.catalog.AbstractExternalDependencyFactory;
import org.gradle.api.internal.catalog.DefaultVersionCatalog;
import java.util.Map;
import javax.inject.Inject;

/**
 * A catalog of dependencies accessible via the `libs` extension.
*/
@NonNullApi
public class LibrariesForLibs extends AbstractExternalDependencyFactory {

    private final AbstractExternalDependencyFactory owner = this;
    private final GrpcLibraryAccessors laccForGrpcLibraryAccessors = new GrpcLibraryAccessors(owner);
    private final ProtobufLibraryAccessors laccForProtobufLibraryAccessors = new ProtobufLibraryAccessors(owner);
    private final TomcatLibraryAccessors laccForTomcatLibraryAccessors = new TomcatLibraryAccessors(owner);
    private final VersionAccessors vaccForVersionAccessors = new VersionAccessors(providers, config);
    private final BundleAccessors baccForBundleAccessors = new BundleAccessors(providers, config);
    private final PluginAccessors paccForPluginAccessors = new PluginAccessors(providers, config);

    @Inject
    public LibrariesForLibs(DefaultVersionCatalog config, ProviderFactory providers) {
        super(config, providers);
    }

        /**
         * Creates a dependency provider for junit (org.junit.jupiter:junit-jupiter)
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getJunit() { return create("junit"); }

    /**
     * Returns the group of libraries at grpc
     */
    public GrpcLibraryAccessors getGrpc() { return laccForGrpcLibraryAccessors; }

    /**
     * Returns the group of libraries at protobuf
     */
    public ProtobufLibraryAccessors getProtobuf() { return laccForProtobufLibraryAccessors; }

    /**
     * Returns the group of libraries at tomcat
     */
    public TomcatLibraryAccessors getTomcat() { return laccForTomcatLibraryAccessors; }

    /**
     * Returns the group of versions at versions
     */
    public VersionAccessors getVersions() { return vaccForVersionAccessors; }

    /**
     * Returns the group of bundles at bundles
     */
    public BundleAccessors getBundles() { return baccForBundleAccessors; }

    /**
     * Returns the group of plugins at plugins
     */
    public PluginAccessors getPlugins() { return paccForPluginAccessors; }

    public static class GrpcLibraryAccessors extends SubDependencyFactory {
        private final GrpcGeneratorLibraryAccessors laccForGrpcGeneratorLibraryAccessors = new GrpcGeneratorLibraryAccessors(owner);
        private final GrpcNettyLibraryAccessors laccForGrpcNettyLibraryAccessors = new GrpcNettyLibraryAccessors(owner);

        public GrpcLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for protobuf (io.grpc:grpc-protobuf)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getProtobuf() { return create("grpc.protobuf"); }

            /**
             * Creates a dependency provider for stub (io.grpc:grpc-stub)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getStub() { return create("grpc.stub"); }

            /**
             * Creates a dependency provider for testing (io.grpc:grpc-testing)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getTesting() { return create("grpc.testing"); }

        /**
         * Returns the group of libraries at grpc.generator
         */
        public GrpcGeneratorLibraryAccessors getGenerator() { return laccForGrpcGeneratorLibraryAccessors; }

        /**
         * Returns the group of libraries at grpc.netty
         */
        public GrpcNettyLibraryAccessors getNetty() { return laccForGrpcNettyLibraryAccessors; }

    }

    public static class GrpcGeneratorLibraryAccessors extends SubDependencyFactory {

        public GrpcGeneratorLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for java (io.grpc:protoc-gen-grpc-java)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getJava() { return create("grpc.generator.java"); }

    }

    public static class GrpcNettyLibraryAccessors extends SubDependencyFactory {

        public GrpcNettyLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for shaded (io.grpc:grpc-netty-shaded)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getShaded() { return create("grpc.netty.shaded"); }

    }

    public static class ProtobufLibraryAccessors extends SubDependencyFactory {

        public ProtobufLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for java (com.google.protobuf:protobuf-java-util)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getJava() { return create("protobuf.java"); }

            /**
             * Creates a dependency provider for protoc (com.google.protobuf:protoc)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getProtoc() { return create("protobuf.protoc"); }

    }

    public static class TomcatLibraryAccessors extends SubDependencyFactory {

        public TomcatLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for annotations (org.apache.tomcat:annotations-api)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getAnnotations() { return create("tomcat.annotations"); }

    }

    public static class VersionAccessors extends VersionFactory  {

        public VersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: grpc (1.51.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getGrpc() { return getVersion("grpc"); }

            /**
             * Returns the version associated to this alias: junit (5.8.1)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getJunit() { return getVersion("junit"); }

            /**
             * Returns the version associated to this alias: proto (3.21.9)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getProto() { return getVersion("proto"); }

    }

    public static class BundleAccessors extends BundleFactory {

        public BundleAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

    }

    public static class PluginAccessors extends PluginFactory {

        public PluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Creates a plugin provider for protobuf to the plugin id 'com.google.protobuf'
             * This plugin was declared in catalog libs.versions.toml
             */
            public Provider<PluginDependency> getProtobuf() { return createPlugin("protobuf"); }

    }

}
