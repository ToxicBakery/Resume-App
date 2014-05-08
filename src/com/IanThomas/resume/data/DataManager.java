package com.IanThomas.resume.data;

import java.util.ArrayList;
import java.util.List;

import com.IanThomas.resume.R;

public class DataManager {

	private static DataManager mInstance;

	private final List<DataProject> mProjects;
	private final List<DataExperience> mExperience;
	private final List<DataCreativeWork> mCreativeWorks;

	private DataManager() {
		mProjects = new ArrayList<>();
		mExperience = new ArrayList<>();
		mCreativeWorks = new ArrayList<>();

		/*
		 * In the future, this data will be loaded externally. For now just hand
		 * jam it in.
		 */
		buildProjects();
		buildExperience();
		buildCreativeWorks();
	}

	public static final synchronized DataManager getInstance() {
		if (mInstance == null)
			mInstance = new DataManager();

		return mInstance;
	}

	public ArrayList<DataProject> getProjects() {
		return new ArrayList<>(mProjects);
	}

	public ArrayList<DataExperience> getExperience() {
		return new ArrayList<>(mExperience);
	}

	public ArrayList<DataCreativeWork> getCreativeWorks() {
		return new ArrayList<>(mCreativeWorks);
	}

	private void buildCreativeWorks() {
		{
			DataCreativeWork.Builder builder = new DataCreativeWork.Builder();
			builder.setImageResource(R.drawable.creative_works_rajawali_examples);
			builder.setTitle("Rajawali Examples");
			builder.setDescription("Application demonstrating common use cases for the Rajawali 3D engine. I rewrote this application from its original version to use the Navigation Drawer as well as provide automated links in the examples linking the user back to the class source on GitHub.");
			builder.setUrl("https://github.com/MasDennis/RajawaliExamples");

			mCreativeWorks.add(builder.build());
		}
		{
			DataCreativeWork.Builder builder = new DataCreativeWork.Builder();
			builder.setImageResource(R.drawable.creative_works_voice_command);
			builder.setTitle("Voice Command");
			builder.setDescription("Open apps with your voice! Access any of your applications in seconds. Customize the names of your applications making them easier to speak. Replace endless pages of apps with a single icon, Voice Command.");
			builder.setUrl("https://play.google.com/store/apps/details?id=com.ToxicBakery.apps.voicecommand");

			mCreativeWorks.add(builder.build());
		}
		{
			DataCreativeWork.Builder builder = new DataCreativeWork.Builder();
			builder.setImageResource(R.drawable.creative_works_bitwave);
			builder.setTitle("Bit Wave");
			builder.setDescription("Bit Wave is a live wallpaper where waves of colorful bits flow across the screen. Bit Wave provides several options for customization including touch, color, physics, and performance.");
			builder.setUrl("https://play.google.com/store/apps/details?id=com.ToxicBakery.lwp.bitwavefree");

			mCreativeWorks.add(builder.build());
		}
		{
			DataCreativeWork.Builder builder = new DataCreativeWork.Builder();
			builder.setImageResource(R.drawable.creative_works_cube_complex);
			builder.setTitle("Cube Complex");
			builder.setDescription("Experience an always changing multi-colored background of shifting cubes that glow with light. The camera and background transition to maintain a clean and unique appearance.");
			builder.setUrl("https://play.google.com/store/apps/details?id=com.ToxicBakery.lwp.cubecomplexfree");

			mCreativeWorks.add(builder.build());
		}
	}

	private void buildProjects() {
		{
			DataProject.Builder builder = new DataProject.Builder();
			builder.setTitle("Presentation on Effective Design Patterns in Android");
			builder.setDescription("Gave an ~30 minute presentation at a local meetup on the topic of design patterns for Android. The goal of the presentation was to provide developers useful insight on approaching common development patterns during application development.");
			builder.setDateFrom("");
			builder.setDateTo("April 2014");
			builder.setUrl("http://www.meetup.com/Central-Florida-Android-Developers-Group/events/175802322/");
			builder.setImageResource(R.drawable.project_meetup);

			mProjects.add(builder.build());
		}
		{
			DataProject.Builder builder = new DataProject.Builder();
			builder.setTitle("Presentation on Rajawali 3D Framework for Android");
			builder.setDescription("Gave an ~20 minute presentation at a local meetup for the Rajawali 3D Engine project I help moderate on Github. The presentation demonstrated some of the applications developed using Rajawali as well as briefly discussing how anyone can get started using the project.");
			builder.setDateFrom("");
			builder.setDateTo("March 2014");
			builder.setUrl("http://www.meetup.com/Central-Florida-Android-Developers-Group/events/170865842/");
			builder.setImageResource(R.drawable.project_meetup);

			mProjects.add(builder.build());
		}
		{
			DataProject.Builder builder = new DataProject.Builder();
			builder.setTitle("Arduino Com");
			builder.setDescription("C# based COM port console tool. I was not a fan of the console provided with the Arduino IDE so I created my own that encompassed the features I needed for my projects. The highlight of this project is the use of GTK allowing for use of the tool across Windows, Mac and Linux.");
			builder.setDateFrom("December 2012");
			builder.setDateTo("");
			builder.setUrl("https://github.com/damnusernames/Arduino-Com");

			mProjects.add(builder.build());
		}
		{
			DataProject.Builder builder = new DataProject.Builder();
			builder.setTitle("Rajawali");
			builder.setDescription("Rajawali is an open source 3D engine supporting GLES2.0 and Android 4.0 and higher. I am a project maintainer and active contributor. I often spend my free time implementing new features, assisting new users, and collaborating with the other 6 maintainers to ensure the engine moves forward in the best direction possible.");
			builder.setDateFrom("April 2012");
			builder.setDateTo("");
			builder.setUrl("https://github.com/MasDennis/Rajawali");

			mProjects.add(builder.build());
		}
		{
			DataProject.Builder builder = new DataProject.Builder();
			builder.setTitle("Toxic Bakery on Google Play");
			builder.setDescription("Toxic Bakery was created by Shawn Adams and myself for the purpose of pursing development interests in Android and elsewhere. Check out what we have created on the Google Play store.");
			builder.setDateFrom("June 2010");
			builder.setDateTo("");
			builder.setUrl("https://play.google.com/store/search?q=pub:%22Toxic+Bakery%22");
			builder.setImageResource(R.drawable.project_toxic_bakery);

			mProjects.add(builder.build());
		}
	}

	private void buildExperience() {
		{
			DataExperience.Builder builder = new DataExperience.Builder();
			builder.setTitle("Android Developer");
			builder.setCompany("IDEAL Technology Corporation");
			builder.setLocation("Orlando, FL");
			builder.setDateFrom("June 2013");
			builder.setDateTo("");
			builder.setDescription("Android developer for digital forensics and data acquisition software.\n\n- Android\n- Java\n- Linux\n- SVN\n- Git");
			builder.setUrl("http://idealcorp.com/");

			mExperience.add(builder.build());
		}
		{
			DataExperience.Builder builder = new DataExperience.Builder();
			builder.setTitle("Lead Developer");
			builder.setCompany("Carley Corporation");
			builder.setLocation("Orlando, FL");
			builder.setDateFrom("February 2012");
			builder.setDateTo("June 2013");
			builder.setDescription("Lead Developer creating emulation software of multifunctional displays for military pilot training.\n\n- Git\n- JIRA\n- AS3\n- C#\n- Android\n- Java\n- XML/XSD\n- HTML\n- SVN");
			builder.setUrl("");

			mExperience.add(builder.build());
		}
		{
			DataExperience.Builder builder = new DataExperience.Builder();
			builder.setTitle("Developer");
			builder.setCompany("Gleim Publications");
			builder.setLocation("Gainesvill, FL");
			builder.setDateFrom("July 2011");
			builder.setDateTo("February 2012");
			builder.setDescription("Worked on a team of thirty developers maintaining and creating content for the Gleim Publications online store. Also worked on updating and creating new course-ware for Accounting and Aviation use. \n\n- Mercurial\n- PHP\n- jQuery\n- jQuery Mobile");
			builder.setUrl("http://www.gleim.com/");

			mExperience.add(builder.build());
		}
		{
			DataExperience.Builder builder = new DataExperience.Builder();
			builder.setTitle("Developer");
			builder.setCompany("Toxic Bakery");
			builder.setLocation("Orlando, FL");
			builder.setDateFrom("June 2010");
			builder.setDateTo("");
			builder.setDescription("Develop and deploy Android applications, live wallpapers, and tools for the Android Play Store.\n\n- Android\n- Java\n- Mercurial\n- GIT");
			builder.setUrl("http://www.toxicbakery.com/");

			mExperience.add(builder.build());
		}
	}
}
