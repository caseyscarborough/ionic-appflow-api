package ionic.appflow;

import ionic.appflow.dto.deployments.Build;
import ionic.appflow.dto.deployments.Deployment;
import ionic.appflow.dto.shared.Automation;
import ionic.appflow.dto.shared.Certificate;
import ionic.appflow.dto.shared.Channel;
import ionic.appflow.dto.shared.Commit;
import ionic.appflow.dto.shared.Config;
import ionic.appflow.dto.shared.Environment;
import ionic.appflow.dto.shared.Ionic;
import ionic.appflow.dto.shared.NativeConfig;
import ionic.appflow.util.TableGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Application {

    public static void main(String[] args) {
        final String token = System.getenv("IONIC_TOKEN");
        final String appId = System.getenv("IONIC_APP_ID");
        final AppflowClient client = new AppflowClient(token);
        printNativeConfigs(client, appId);
        printSigningCertificates(client, appId);
        printEnvironments(client, appId);
        printAutomations(client, appId);
        printChannels(client, appId);
        printCommits(client, appId);
        printBuilds(client, appId);
        printDeployments(client, appId);
    }

    private static void printNativeConfigs(AppflowClient client, String appId) {
        final List<NativeConfig> configs = client.getNativeConfigs(appId);
        TableGenerator table = new TableGenerator(
            "Name",
            "General Config",
            "Ionic Cordova Plugin Config"
        ).withUnicode(true);

        for (NativeConfig config : configs) {
            final Ionic ionic = config.getIonic();
            TreeMap<String, String> generalConfigs = new TreeMap<>();
            TreeMap<String, Object> ionicConfigs = new TreeMap<>();

            generalConfigs.put("BUNDLE_ID", config.getBase().getBundleId());
            generalConfigs.put("NAME", config.getBase().getName());

            ionicConfigs.put("appId", ionic.getAppId());
            ionicConfigs.put("CHANNEL_NAME", ionic.getChannelName());
            ionicConfigs.put("UPDATE_METHOD", ionic.getUpdateMethod());
            ionicConfigs.put("MAX_STORE", ionic.getMaxStore());
            ionicConfigs.put("MIN_BACKGROUND_DURATION", ionic.getMinBackgroundDuration());
            ionicConfigs.put("Disable Deploy", ionic.isDisableDeploy());

            final List<String> gKeys = new ArrayList<>(generalConfigs.keySet());
            final List<String> iKeys = new ArrayList<>(ionicConfigs.keySet());
            for (int i = 0; i < ionicConfigs.size(); i++) {
                table.addRow(
                    i == 0 ? config.getName() : "",
                    i < gKeys.size() ? gKeys.get(i) + "=" + generalConfigs.get(gKeys.get(i)) : "",
                    i < iKeys.size() ? iKeys.get(i) + "=" + ionicConfigs.get(iKeys.get(i)) : ""
                );
            }
        }
        table.print();
    }

    private static void printSigningCertificates(final AppflowClient client, String appId) {
        final List<Certificate> certificates = client.getSigningCertificates(appId);
        TableGenerator table = new TableGenerator(
            "Name",
            "Type",
            "Platforms"
        ).sortBy(0).withUnicode(true);
        for (Certificate certificate : certificates) {
            StringBuilder platforms = new StringBuilder();
            if (certificate.getCredentials().getIos() != null) {
                platforms.append("iOS");
            }
            if (certificate.getCredentials().getAndroid() != null) {
                if (platforms.length() > 0) {
                    platforms.append(", ");
                }
                platforms.append("Android");
            }
            table.addRow(
                certificate.getName(),
                certificate.getType(),
                platforms.toString()
            );
        }
        table.print();
    }

    private static void printEnvironments(final AppflowClient client, String appId) {
        final List<Environment> environments = client.getEnvironments(appId);
        System.out.println("\n\nEnvironments");
        TableGenerator table = new TableGenerator(
            "Name",
            "Secrets",
            "Variables"
        ).sortBy(0).withUnicode(true);
        for (Environment environment : environments) {
            table.addRow(
                environment.getName(),
                environment.getSecrets().stream().map(Config::getName).collect(Collectors.joining(", ")),
                environment.getConfig().stream().map(c -> c.getName() + "=" + c.getValue()).collect(Collectors.joining(", "))
            );
        }
        table.print();
    }

    private static void printAutomations(final AppflowClient client, String appId) {
        final List<Automation> automations = client.getAutomations(appId);
        System.out.println("\n\nAutomations");
        TableGenerator table = new TableGenerator(
            "Name",
            "Branch",
            "Job",
            "Webhook",
            "Environment",
            "Native Config",
            "Destination"
        ).sortBy(0).withUnicode(true);
        for (Automation automation : automations) {
            table.addRow(
                automation.getName(),
                automation.getRef(),
                automation.getPlatform(),
                automation.getNotifications() != null && !automation.getNotifications().isEmpty() ?
                    automation.getNotifications().get(0).getUrl() : "--",
                automation.getEnvironment().getName(),
                automation.getNativeConfig() != null ? automation.getNativeConfig().getName() : "--",
                automation.getChannels() != null && !automation.getChannels().isEmpty() ?
                    automation.getChannels().stream().map(Channel::getName).collect(Collectors.joining(", ")) : "--"
            );
        }
        table.print();
    }

    private static void printChannels(final AppflowClient client, String appId) {
        final List<Channel> channels = client.getChannels(appId);
        System.out.println("\n\nChannels");
        TableGenerator table = new TableGenerator(
            "Name",
            "Active Build"
        ).sortBy(0).withUnicode(true);
        for (Channel channel : channels) {
            table.addRow(
                channel.getName(),
                channel.getSnapshot() != null ? "#" + channel.getSnapshot().getNumber() : "--"
            );
        }
        table.print();
    }

    private static void printCommits(final AppflowClient client, String appId) {
        final List<Commit> commits = client.getCommits(appId);
        System.out.println("\n\nCommits");
        TableGenerator table = new TableGenerator(
            "Commit Hash",
            "Committed By",
            "Committed At",
            "Ref",
            "Ref Type"
        ).sortBy(2, true).withUnicode(true);
        for (Commit commit : commits) {
            table.addRow(
                commit.getShortSha(),
                commit.getUser().getName(),
                commit.getCreated(),
                commit.getRef(),
                commit.getRefType()
            );
        }
        table.print();
    }

    private static void printBuilds(final AppflowClient client, String appId) {
        final List<Build> builds = client.getBuilds(appId);
        System.out.println("\n\nBuilds");
        TableGenerator table = new TableGenerator(
            "Build",
            "Status",
            "Platform",
            "Triggered By",
            "Commit",
            "Deployment"
        ).sortBy(1, true).withUnicode(true);

        for (Build build : builds) {
            table.addRow(
                "#" + build.getNumber(),
                build.getStatus(),
                build.getPlatform(),
                build.getAutomationName(),
                build.getCommit().getShortSha(),
                build.getDeployments().stream().map(d -> d.getChannel().getName()).collect(Collectors.joining(","))
            );
        }
        table.print();
    }

    private static void printDeployments(final AppflowClient client, String appId) {
        final List<Deployment> deployments = client.getDeployments(appId);
        System.out.println("\n\nDeployments");
        TableGenerator table = new TableGenerator(
            "Triggered By",
            "User",
            "Type",
            "Status",
            "Channel",
            "Build",
            "Commit",
            "Deployed At"
        ).sortBy(7, true).withUnicode(true);
        for (Deployment deployment : deployments) {
            table.addRow(
                deployment.getTriggeredBy(),
                deployment.getUser().getName(),
                deployment.getType(),
                deployment.getStatus(),
                deployment.getChannel().getName(),
                String.valueOf(deployment.getBuild().getJobId()),
                deployment.getBuild().getCommit().getShortSha() + " @ " + deployment.getBuild().getCommit().getRef(),
                deployment.getCompleted()
            );
        }
        table.print();
    }
}
