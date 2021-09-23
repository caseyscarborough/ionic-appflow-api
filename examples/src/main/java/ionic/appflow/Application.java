package ionic.appflow;

import ionic.appflow.model.App;
import ionic.appflow.model.Automation;
import ionic.appflow.model.Build;
import ionic.appflow.model.BuildType;
import ionic.appflow.model.Certificate;
import ionic.appflow.model.Channel;
import ionic.appflow.model.Commit;
import ionic.appflow.model.Config;
import ionic.appflow.model.Deployment;
import ionic.appflow.model.Environment;
import ionic.appflow.model.Ionic;
import ionic.appflow.model.NativeConfig;
import ionic.appflow.model.Organization;
import ionic.appflow.model.Snapshot;
import ionic.appflow.model.Stack;
import ionic.appflow.model.User;
import ionic.appflow.util.TableGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Application {

    public static void main(String[] args) {
        final String token = System.getenv("IONIC_TOKEN");
        final String appId = System.getenv("IONIC_APP_ID");
        final String org = System.getenv("IONIC_ORG");
        if (token == null || appId == null || org == null) {
            throw new IllegalStateException("IONIC_TOKEN, IONIC_APP_ID, and IONIC_ORG environment variables required.");
        }

        final AppflowClient client = new AppflowClient(token);
        printOrganization(client, org);
        printUsers(client, org);
        printApp(client, appId);
        printApps(client);
        printStacks(client, appId);
        printNativeConfigs(client, appId);
        printSigningCertificates(client, appId);
        printEnvironments(client, appId);
        printAutomations(client, appId);
        printAutomation(client, appId);
        printChannels(client, appId);
        printChannel(client, appId);
        printCommits(client, appId);
        printBuilds(client, appId);
        printBuild(client, appId);
        printDeployments(client, appId);
        printDeployment(client, appId);
    }

    private static void printUsers(AppflowClient client, String org) {
        final Organization organization = client.getOrganization(org);
        final List<User> users = client.getUsers(organization.getId());

        TableGenerator table = new TableGenerator(
            "ID",
            "Name",
            "Username",
            "Email",
            "Role"
        ).withUnicode(true);

        for (User user : users) {
            table.addRow(
                String.valueOf(user.getId()),
                user.getName(),
                user.getUsername(),
                user.getEmail(),
                user.getRole()
            );
        }

        System.out.println("\n\nUsers");
        table.print();
    }

    private static void printOrganization(AppflowClient client, String slug) {
        Organization organization = client.getOrganization(slug);
        TableGenerator table = new TableGenerator(
            "Name",
            "Slug",
            "Members",
            "Plan",
            "Description"
        ).withUnicode(true);
        table.addRow(
            organization.getName(),
            organization.getSlug(),
            String.valueOf(organization.getMemberTotal()),
            organization.getPlan(),
            organization.getDescription()
        );
        System.out.println("\n\nOrganization");
        table.print();
    }

    private static void printApp(final AppflowClient client, final String appId) {
        App app = client.getApp(appId);
        TableGenerator table = new TableGenerator(
            "ID",
            "Name",
            "Slug",
            "Web Preview",
            "Last Activity"
        ).withUnicode(true);
        table.addRow(
            app.getId(),
            app.getName(),
            app.getSlug(),
            String.valueOf(app.isWebPreview()),
            app.getLastActivity()
        );
        System.out.println("\n\nApp");
        table.print();
    }

    private static void printApps(AppflowClient client) {
        final List<App> apps = client.getApps();
        TableGenerator table = new TableGenerator(
            "ID",
            "Name",
            "Slug",
            "Web Preview",
            "Last Activity"
        ).withUnicode(true);
        for (App app : apps) {
            table.addRow(
                app.getId(),
                app.getName(),
                app.getSlug(),
                String.valueOf(app.isWebPreview()),
                app.getLastActivity()
            );
        }
        System.out.println("\n\nApps");
        table.print();
    }

    private static void printStacks(AppflowClient client, String appId) {
        final List<Stack> stacks = client.getStacks();
        TableGenerator table = new TableGenerator(
            "Platform",
            "Name",
            "Friendly Name",
            "Latest",
            "Build Types"
        ).withUnicode(true);
        for (Stack stack : stacks) {
            table.addRow(
                stack.getPlatform().getFriendlyName(),
                stack.getName(),
                stack.getFriendlyName(),
                String.valueOf(stack.isLatest()),
                stack.getBuildTypes().stream().map(BuildType::getFriendlyName).collect(Collectors.joining(", "))
            );
        }
        System.out.println("\n\nStacks");
        table.print();
    }

    private static void printChannel(AppflowClient client, String appId) {
        final List<Channel> channels = client.getChannels(appId);
        if (channels.isEmpty()) {
            return;
        }

        final Channel channel = client.getChannel(appId, channels.get(0).getId());
        TableGenerator table = new TableGenerator(
            "Name",
            "Created",
            "Status",
            "App ID",
            "Build"
        ).withUnicode(true);

        final Snapshot snap = channel.getSnapshot();
        table.addRow(
            channel.getName(),
            channel.getCreated(),
            snap != null ? snap.getStatus() : "--",
            snap != null ? snap.getAppId() : "--",
            snap != null ? snap.getShortSha() : "--"
        );
        System.out.println("\n\nChannel " + channel.getId());
        table.print();
    }

    private static void printBuild(AppflowClient client, String appId) {
        final List<Build> builds = client.getBuilds(appId);
        if (builds.isEmpty()) {
            return;
        }
        final Build build = client.getBuild(appId, builds.get(0).getJobId());
        TableGenerator table = new TableGenerator(
            "Build",
            "Platform",
            "Build Stack",
            "Deployment",
            "Artifacts",
            "Commit"
        ).withUnicode(true);
        table.addRow(
            "#" + build.getNumber(),
            build.getPlatform().getFriendlyName(),
            build.getStack().getFriendlyName(),
            build.getDeployments() != null && !build.getDeployments().isEmpty() ?
                build.getDeployments().get(0).getChannel().getName() : "--",
            build.getArtifactName(),
            build.getCommit().getShortSha()
        );
        System.out.println("\n\nBuild " + build.getId());
        table.print();
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
        System.out.println("\n\nNative Configs");
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
        System.out.println("\n\nSigning Certificates");
        table.print();
    }

    private static void printEnvironments(final AppflowClient client, String appId) {
        final List<Environment> environments = client.getEnvironments(appId);
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
        System.out.println("\n\nEnvironments");
        table.print();
    }

    private static void printAutomation(final AppflowClient client, final String appId) {
        final List<Automation> automations = client.getAutomations(appId);
        if (automations.isEmpty()) {
            return;
        }

        Automation automation = client.getAutomation(appId, automations.get(0).getId());
        TableGenerator table = new TableGenerator(
            "Name",
            "Platform",
            "Ref",
            "Webhook",
            "Build Stack",
            "Channel(s)",
            "Environment"
        ).withUnicode(true);
        table.addRow(
            automation.getName(),
            automation.getPlatform().getFriendlyName(),
            automation.getRef(),
            automation.getNotifications() != null && !automation.getNotifications().isEmpty() ?
                automation.getNotifications().get(0).getUrl() : "--",
            automation.getStack() != null ? automation.getStack().getFriendlyName() : "--",
            automation.getChannels() != null ?
                automation.getChannels().stream().map(Channel::getName).collect(Collectors.joining(", ")) : "--",
            automation.getEnvironment().getName()
        );
        System.out.println("\n\nAutomation");
        table.print();
    }

    private static void printAutomations(final AppflowClient client, String appId) {
        final List<Automation> automations = client.getAutomations(appId);
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
                automation.getPlatform().getFriendlyName(),
                automation.getNotifications() != null && !automation.getNotifications().isEmpty() ?
                    automation.getNotifications().get(0).getUrl() : "--",
                automation.getEnvironment().getName(),
                automation.getNativeConfig() != null ? automation.getNativeConfig().getName() : "--",
                automation.getChannels() != null && !automation.getChannels().isEmpty() ?
                    automation.getChannels().stream().map(Channel::getName).collect(Collectors.joining(", ")) : "--"
            );
        }
        System.out.println("\n\nAutomations");
        table.print();
    }

    private static void printChannels(final AppflowClient client, String appId) {
        final List<Channel> channels = client.getChannels(appId);
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
        System.out.println("\n\nChannels");
        table.print();
    }

    private static void printCommits(final AppflowClient client, String appId) {
        final List<Commit> commits = client.getCommits(appId);
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
        System.out.println("\n\nCommits");
        table.print();
    }

    private static void printBuilds(final AppflowClient client, String appId) {
        final List<Build> builds = client.getBuilds(appId);
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
                build.getPlatform().getFriendlyName(),
                build.getAutomationName(),
                build.getCommit().getShortSha(),
                build.getDeployments().stream().map(d -> d.getChannel().getName()).collect(Collectors.joining(","))
            );
        }
        System.out.println("\n\nBuilds");
        table.print();
    }

    private static void printDeployment(final AppflowClient client, String appId) {
        final List<Deployment> deployments = client.getDeployments(appId);
        if (deployments.isEmpty()) {
            return;
        }

        Deployment deployment = client.getDeployment(appId, deployments.get(0).getId());
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
        System.out.println("\n\nDeployment");
        table.print();
    }

    private static void printDeployments(final AppflowClient client, String appId) {
        final List<Deployment> deployments = client.getDeployments(appId);
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
        System.out.println("\n\nDeployments");
        table.print();
    }
}
