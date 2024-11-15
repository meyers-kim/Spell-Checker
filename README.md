# Spell Checker Web Application

This is a basic web app for checking spelling errors, created using Java, JSF, and EJB. It uses a spell-checking tool to find mistakes, suggest corrections, and display stats on common errors.

## Features

- **Live Spell Check**: As you type, the app tries to catch spelling mistakes in real-time and shows any errors it finds below the text area.
- **Manual Spell Check Button**: There’s a "Check Spelling" button for a full check on the entire text. This is useful if you paste a large chunk of text in one go. If you’re typing a sentence, though, you have to wait until the live checker is done before pressing "Check Spelling."
- **Error Statistics**: After using "Check Spelling," the "View Statistics" button enables that shows the most common mistakes found in your latest input.

## How to Use

1. **Enter Text**:
   - Type or paste text into the area labeled "Enter text for spell check."
   - If you’re typing, the app will start checking in the background, showing any errors below.
   - If you paste a sentence, you can immediately press "Check Spelling" without waiting.

2. **Live Spell Check**:
   - As you type, the app does its best to keep up, but you need to wait for it to finish before hitting "Check Spelling." 
   - When pasting, though, you can click "Check Spelling" right away.
   - The live spell check could easily be removed, if necassary.

3. **Manual Spell Check**:
   - Click **Check Spelling** to fully check your entire text.
   - This button refreshes the error list with all mistakes found in your text.
   - Once clicked, the **View Statistics** button will be enabled.

4. **View Statistics**:
   - After running a full check, click **View Statistics** to see the most common mistakes and their corrections from your recent text.

## Notes

- **Live Checker**: You can’t hit "Check Spelling" while typing until the live checker finishes. This restriction doesn’t apply if you paste text.
- **Statistics Reset**: Stats are cleared each time you press **Check Spelling**, so only the latest errors are displayed.

## Technical Details

- **Backend**: Java EE (Jakarta EE) with EJB and JPA for managing data.
- **Frontend**: JSF (JavaServer Faces) for a simple interface.
- **Spell-Checking Service**: British English spell-checking is handled by an integrated service LanguageTool.