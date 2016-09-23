package com.game.gaika.profile;

//import my.TextBox;

import android.util.Log;


	public class Profile {
		public class ProfileSample {

			boolean bValid; // Whether this data is valid
			int iProfileInstances; // # of times ProfileBegin called
			int iOpenProfiles; // # of times ProfileBegin w/o ProfileEnd
			String szName = new String(); // Name of sample
			double fStartTime; // The current open profile start time
			double fAccumulator; // All samples this frame added together
			double fChildrenSampleTime; // Time taken by all children
			int iNumParents; // Number of profile parents

			public ProfileSample() {

			}
		};

		public class ProfileSampleHistory {
			boolean bValid; // Whether the data is valid
			String szName = new String(); // Name of the sample
			double fAve; // Average time per frame (percentage)
			double fMin; // Minimum time per frame (percentage)
			double fMax; // Maximum time per frame (percentage)

			public ProfileSampleHistory() {

			}
		};

		static int NUM_PROFILE_SAMPLES = 50;
		static ProfileSample g_samples[] = new ProfileSample[NUM_PROFILE_SAMPLES];
		static ProfileSampleHistory g_history[] = new ProfileSampleHistory[NUM_PROFILE_SAMPLES];
		static double g_startProfile = 0;
		static double g_endProfile = 0;
		static StringBuffer textBox;

		static private double getExactTime() {
			return (double )System.nanoTime() / 1000000000.0f;
		}

		static public void init() {
			int i;

			for (i = 0; i < NUM_PROFILE_SAMPLES; i++) {
				g_samples[i] =  (new Profile()).new ProfileSample();
				g_history[i] = (new Profile()).new ProfileSampleHistory();
			}

			for (i = 0; i < NUM_PROFILE_SAMPLES; i++) {
				g_samples[i].bValid = false;
				g_history[i].bValid = false;
			}

			g_startProfile = getExactTime();

			textBox = new StringBuffer();


		}

		static public void begin(String name) {
			int i = 0;

			while (i < NUM_PROFILE_SAMPLES && g_samples[i].bValid == true) {
				if (name.equals(g_samples[i].szName) == true) {
					// Found the sample
					g_samples[i].iOpenProfiles++;
					g_samples[i].iProfileInstances++;
					g_samples[i].fStartTime = getExactTime();
					assert g_samples[i].iOpenProfiles == 1; // max 1 open at once
					return;
				}
				i++;
			}

			if (i >= NUM_PROFILE_SAMPLES) {
				assert false : "Exceeded Max Available Profile Samples";
				return;
			}

			g_samples[i].szName = name;
			g_samples[i].bValid = true;
			g_samples[i].iOpenProfiles = 1;
			g_samples[i].iProfileInstances = 1;
			g_samples[i].fAccumulator = 0;
			g_samples[i].fStartTime = getExactTime();
			g_samples[i].fChildrenSampleTime = 0;
		}

		static public void end(String name) {
			int i = 0;
			int numParents = 0;

			while (i < NUM_PROFILE_SAMPLES && g_samples[i].bValid == true) {
				if (name.equals(g_samples[i].szName) == true) { // Found the sample
					int inner = 0;
					int parent = -1;
					double fEndTime = getExactTime();
					g_samples[i].iOpenProfiles--;

					// Count all parents and find the immediate parent
					while (g_samples[inner].bValid == true) {
						if (g_samples[inner].iOpenProfiles > 0) { // Found a parent
							// (any open
							// profiles are
							// parents)
							numParents++;
							if (parent < 0) { // Replace invalid parent (index)
								parent = inner;
							} else if (g_samples[inner].fStartTime >= g_samples[parent].fStartTime) { // Replace
								// with
								// more
								// immediate
								// parent
								parent = inner;
							}
						}
						inner++;
					}

					// Remember the current number of parents of the sample
					g_samples[i].iNumParents = numParents;

					if (parent >= 0) { // Record this time in fChildrenSampleTime
						// (add it in)
						g_samples[parent].fChildrenSampleTime += fEndTime - g_samples[i].fStartTime;
					}

					// Save sample time in accumulator
					g_samples[i].fAccumulator += fEndTime - g_samples[i].fStartTime;
					return;
				}
				i++;
			}
		}

		static public void dumpOutputToBuffer() {
			int i = 0;

			g_endProfile = getExactTime();
			textBox.setLength(0);

			textBox.append("-\n");
			textBox.append("  Ave :   Min :   Max :   # : Profile Name\n");
			textBox.append("--------------------------------------------\n");
			// System.out.print

			while (i < NUM_PROFILE_SAMPLES && g_samples[i].bValid == true) {
				int indent = 0;
				double sampleTime, percentTime, aveTime, minTime, maxTime;
				String line, name, indentedName;
				String ave, min, max, num;

				if (g_samples[i].iOpenProfiles < 0) {
					assert false : "ProfileEnd() called without a ProfileBegin()";
				} else if (g_samples[i].iOpenProfiles > 0) {
					assert false : "ProfileBegin() called without a ProfileEnd()";
				}

				sampleTime = g_samples[i].fAccumulator - g_samples[i].fChildrenSampleTime;
				percentTime = (sampleTime / (g_endProfile - g_startProfile)) * 100.0f;

				aveTime = minTime = maxTime = percentTime;

				// Add new measurement into the history and get the ave, min, and
				// max
				StoreProfileInHistory(g_samples[i].szName, percentTime);
				GetProfileFromHistory(g_samples[i].szName, aveTime, minTime, maxTime);

				// Format the data
				ave = String.format("%3.1f", aveTime);
				min = String.format("%3.1f", minTime);
				max = String.format("%3.1f", maxTime);
				num = String.format("%3d", g_samples[i].iProfileInstances);

				indentedName = g_samples[i].szName;
				for (indent = 0; indent < g_samples[i].iNumParents; indent++) {
					name = "   " + indentedName;
					indentedName = name;
				}

				line = String.format("%5s : %5s : %5s : %3s : %s\n", ave, min, max, num, indentedName);
				textBox.append(line); // Send the line to text buffer
				i++;
			}

			{ // Reset samples for next frame

				for (int j = 0; j < NUM_PROFILE_SAMPLES; j++) {
					g_samples[j].bValid = false;
				}
				g_startProfile = getExactTime();
			}
		}

		static public void StoreProfileInHistory(String name, double percent) {
			int i = 0;
			double oldRatio;
			double newRatio = 0.8f * GetElapsedTime();
			if (newRatio > 1.0f) {
				newRatio = 1.0f;
			}
			oldRatio = 1.0f - newRatio;

			while (i < NUM_PROFILE_SAMPLES && g_history[i].bValid == true) {
				if (name.equals(g_history[i].szName) == true) { // Found the sample
					g_history[i].fAve = (g_history[i].fAve * oldRatio) + (percent * newRatio);
					if (percent < g_history[i].fMin) {
						g_history[i].fMin = percent;
					} else {
						g_history[i].fMin = (g_history[i].fMin * oldRatio) + (percent * newRatio);
					}

					if (g_history[i].fMin < 0.0f) {
						g_history[i].fMin = 0.0f;
					}

					if (percent > g_history[i].fMax) {
						g_history[i].fMax = percent;
					} else {
						g_history[i].fMax = (g_history[i].fMax * oldRatio) + (percent * newRatio);
					}
					return;
				}
				i++;
			}

			if (i < NUM_PROFILE_SAMPLES) { // Add to history
				g_history[i].szName = name;
				g_history[i].bValid = true;
				g_history[i].fAve = g_history[i].fMin = g_history[i].fMax = percent;
			} else {
				assert false : "Exceeded Max Available Profile Samples!";
			}
		}

		static public void GetProfileFromHistory(String name, double ave, double min, double max) {
			int i = 0;
			while (i < NUM_PROFILE_SAMPLES && g_history[i].bValid == true) {
				if (name.equals(g_history[i].szName) == true) { // Found the sample
					ave = g_history[i].fAve;
					min = g_history[i].fMin;
					max = g_history[i].fMax;
					return;
				}
				i++;
			}
			ave = min = max = 0.0f;
		}

		static double g_TimeLastTick = -1.0f;

		static double  GetElapsedTime() {
			return (g_TimeLastTick);
		}

	static public void draw() {
		if (textBox != null) {
			// textBox->Printf( "Yippee Yahoo!!!\n" );
			Log.d("Gaika", textBox.toString());
			Log.d("Gaika", textBox.toString());
		}

	}

}
