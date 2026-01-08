package com.example.jwtauth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;

@RestController
@RequestMapping("/jwt")
public class HelloController {
    @GetMapping("/admin")
    public String adminHello() {
        return "Hello Admin!";
    }

    @GetMapping("/user")
    public String userHello() {
        return "Hello User!";
    }
    @GetMapping("")
    public String health() {
        return "UP";
    }
    @GetMapping("/front")
    public ResponseEntity<String> htmlPage(){
        String html = """
        <!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Hierarchy Extractor</title>
    <!-- Modern Font -->
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    <style>
        :root {
            --primary: #6366f1;
            --primary-hover: #4f46e5;
            --bg: #0f172a;
            --card-bg: #1e293b;
            --text-main: #f8fafc;
            --text-sub: #94a3b8;
            --border: #334155;
            --code-bg: #0f172a;
        }

        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
        }

        body {
            font-family: 'Inter', sans-serif;
            background-color: var(--bg);
            color: var(--text-main);
            height: 100vh;
            display: flex;
            flex-direction: column;
        }

        header {
            padding: 1.5rem 2rem;
            border-bottom: 1px solid var(--border);
            background: rgba(30, 41, 59, 0.8);
            backdrop-filter: blur(10px);
            display: flex;
            align-items: center;
            justify-content: space-between;
        }

        h1 {
            font-size: 1.25rem;
            font-weight: 600;
            color: var(--text-main);
            display: flex;
            align-items: center;
            gap: 0.5rem;
        }

        h1 span {
            color: var(--primary);
        }

        .container {
            flex: 1;
            display: flex;
            padding: 2rem;
            gap: 2rem;
            overflow: hidden;
        }

        /* Upload Section */
        .upload-section {
            flex: 1;
            display: flex;
            flex-direction: column;
            gap: 1rem;
            max-width: 400px;
        }

        .drop-zone {
            flex: 1;
            border: 2px dashed var(--border);
            border-radius: 12px;
            background: var(--card-bg);
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            cursor: pointer;
            transition: all 0.2s ease;
            position: relative;
            min-height: 200px;
        }

        .drop-zone:hover,
        .drop-zone.dragover {
            border-color: var(--primary);
            background: rgba(99, 102, 241, 0.05);
        }

        .drop-zone input {
            position: absolute;
            width: 100%;
            height: 100%;
            opacity: 0;
            cursor: pointer;
        }

        .drop-zone-text {
            color: var(--text-sub);
            text-align: center;
            pointer-events: none;
        }

        .drop-zone-icon {
            font-size: 3rem;
            color: var(--text-sub);
            margin-bottom: 1rem;
        }

        /* Results Section */
        .results-section {
            flex: 3;
            display: flex;
            gap: 1.5rem;
            background: var(--card-bg);
            border-radius: 12px;
            border: 1px solid var(--border);
            padding: 1.5rem;
            overflow: hidden;
            opacity: 0.5;
            pointer-events: none;
            transition: opacity 0.3s ease;
        }

        .results-section.active {
            opacity: 1;
            pointer-events: all;
        }

        .image-view {
            flex: 1;
            display: flex;
            flex-direction: column;
            gap: 1rem;
            overflow: hidden;
        }

        .image-view h3 {
            font-size: 0.875rem;
            text-transform: uppercase;
            letter-spacing: 0.05em;
            color: var(--text-sub);
        }

        .image-container {
            flex: 1;
            background: #000;
            border-radius: 8px;
            overflow: hidden;
            position: relative;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .image-container img {
            max-width: 100%;
            max-height: 100%;
            object-fit: contain;
        }

        .json-view {
            flex: 1;
            display: flex;
            flex-direction: column;
            gap: 1rem;
            max-width: 500px;
        }

        pre {
            background: var(--code-bg);
            padding: 1rem;
            border-radius: 8px;
            overflow-y: auto;
            flex: 1;
            font-family: 'Consolas', 'Monaco', monospace;
            font-size: 0.85rem;
            color: #e2e8f0;
            border: 1px solid var(--border);
        }

        /* Syntax Highlighting */
        .string {
            color: #a5d6ff;
        }

        .number {
            color: #d2a8ff;
        }

        .boolean {
            color: #ff7b72;
        }

        .null {
            color: #ff7b72;
        }

        .key {
            color: #7ee787;
        }

        /* Loader */
        .loader-overlay {
            position: fixed;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
            background: rgba(15, 23, 42, 0.8);
            backdrop-filter: blur(4px);
            display: flex;
            align-items: center;
            justify-content: center;
            z-index: 1000;
            opacity: 0;
            pointer-events: none;
            transition: opacity 0.2s;
        }

        .loader-overlay.visible {
            opacity: 1;
            pointer-events: all;
        }

        .spinner {
            width: 40px;
            height: 40px;
            border: 3px solid rgba(255, 255, 255, 0.1);
            border-radius: 50%;
            border-top-color: var(--primary);
            animation: spin 1s ease-in-out infinite;
        }

        @keyframes spin {
            to {
                transform: rotate(360deg);
            }
        }

        .tabs {
            display: flex;
            gap: 1rem;
            margin-bottom: 1rem;
            border-bottom: 1px solid var(--border);
        }

        .tab {
            padding: 0.75rem 1.5rem;
            color: var(--text-sub);
            cursor: pointer;
            border-bottom: 2px solid transparent;
            transition: all 0.2s;
        }

        .tab:hover {
            color: var(--text-main);
        }

        .tab.active {
            color: var(--primary);
            border-bottom-color: var(--primary);
            font-weight: 600;
        }

        /* Overridden by JS inline style or class toggle */
        .page-block {
            display: none;
            flex: 1;
            gap: 2rem;
            height: 100%;
            overflow: hidden;
        }

        .page-block.active {
            display: flex;
        }

        .compare-container {
            display: flex;
            flex-direction: column;
            gap: 1.5rem;
            flex: 1;
            width: 100%;
            overflow-y: auto;
            /* Allow creating scrollbar if content is tall */
            padding-right: 0.5rem;
        }

        .diff-box {
            background: var(--card-bg);
            border: 1px solid var(--border);
            border-radius: 8px;
            padding: 1rem;
            flex: 1;
            display: flex;
            flex-direction: column;
            gap: 0.5rem;
            min-height: 200px;
            /* Ensure boxes don't shrink too much */
        }

        .diff-title {
            font-weight: 600;
            color: var(--text-sub);
            text-transform: uppercase;
            font-size: 0.8rem;
        }

        .diff-list {
            overflow-y: auto;
            flex: 1;
            min-height: 0;
            /* Needed for flex scrolling */
        }

        .diff-item {
            padding: 0.5rem;
            border-bottom: 1px solid var(--border);
            font-size: 0.9rem;
            word-break: break-word;
            /* Prevent cutoff long names */
        }

        .rel-row {
            display: flex;
            gap: 1rem;
            width: 100%;
            /* Allow wrapping on small screens? No, user has wide screen likely. */
            flex: 1;
            min-height: 400px;
            /* Force minimum height for relationship row */
            flex-shrink: 0;
        }

        .diff-missing {
            color: #f87171;
        }

        .diff-unexpected {
            color: #fbbf24;
        }

        .diff-mismatch {
            color: #60a5fa;
        }
    </style>
</head>

<body>

    <header>
        <h1>Hierarchy Extractor</h1>
    </header>

    <div class="container" style="flex-direction: column;">

        <div class="tabs">
            <div class="tab active" onclick="switchTab('extract')">Extract</div>
            <div class="tab" onclick="switchTab('compare')">Compare</div>
        </div>

        <!-- EXTRACT TAB -->
        <div id="tab-extract" class="page-block active">
            <div class="upload-section">
                <div class="drop-zone" id="dropZone">
                    <input type="file" id="fileInput" accept="image/png, image/jpeg, image/jpg">
                    <div class="drop-zone-icon">📁</div>
                    <div class="drop-zone-text">
                        <strong>Click to upload</strong> or drag and drop<br>
                        <span style="font-size: 0.8em; opacity: 0.7">PNG, JPG up to 10MB</span>
                    </div>
                </div>
            </div>

            <div class="results-section" id="resultsSection">
                <div class="image-view">
                    <h3>Visualization</h3>
                    <div class="image-container">
                        <img id="resultImage" src="" alt="Processed Chart">
                    </div>
                </div>

                <div class="json-view">
                    <h3>Extracted Data</h3>
                    <pre id="jsonOutput"></pre>
                </div>
            </div>
        </div>

        <!-- COMPARE TAB -->
        <div id="tab-compare" class="page-block">
            <div class="upload-section" style="max-width: 300px;">
                <h3>Step 1: Get Extraction</h3>
                <p style="color: var(--text-sub); font-size: 0.9rem; margin-bottom: 1rem;">
                    First, extract an image in the Extract tab. The result is stored automatically.
                </p>

                <h3>Step 2: Upload Truth</h3>
                <div class="drop-zone" style="min-height: 100px; border-color: var(--border);" id="compareDropZone">
                    <input type="file" id="compareInput" accept=".json">
                    <div class="drop-zone-text">Upload Ground Truth JSON</div>
                </div>

                <button onclick="runComparison()" style="
                    margin-top: 1rem;
                    padding: 0.75rem;
                    background: var(--primary);
                    color: white;
                    border: none;
                    border-radius: 6px;
                    cursor: pointer;
                    font-weight: 600;
                ">Run Comparison</button>
            </div>

            <div class="compare-container" id="compareResults">
            </div>
        </div>

    </div>

    <div class="loader-overlay" id="loader">
        <div class="spinner"></div>
    </div>

    <script>
        const dropZone = document.getElementById('dropZone');
        const fileInput = document.getElementById('fileInput');
        const resultsSection = document.getElementById('resultsSection');
        const resultImage = document.getElementById('resultImage');
        const jsonOutput = document.getElementById('jsonOutput');
        const loader = document.getElementById('loader');

        let currentExtractionData = null;

        function switchTab(tabId) {
            document.querySelectorAll('.tab').forEach(t => t.classList.remove('active'));
            document.querySelectorAll('.page-block').forEach(p => p.classList.remove('active'));

            // Find tab by text content (lazy way) or add data attributes. 
            // Better: use onclick elements
            const tabs = document.querySelectorAll('.tab');
            if (tabId === 'extract') tabs[0].classList.add('active');
            if (tabId === 'compare') tabs[1].classList.add('active');

            document.getElementById('tab-' + tabId).classList.add('active');
        }

        // Drag & Drop Visuals
        ['dragenter', 'dragover'].forEach(eventName => {
            dropZone.addEventListener(eventName, (e) => {
                e.preventDefault();
                dropZone.classList.add('dragover');
            }, false);
        });

        ['dragleave', 'drop'].forEach(eventName => {
            dropZone.addEventListener(eventName, (e) => {
                e.preventDefault();
                dropZone.classList.remove('dragover');
            }, false);
        });

        dropZone.addEventListener('drop', (e) => {
            const dt = e.dataTransfer;
            const files = dt.files;
            handleFiles(files);
        });

        fileInput.addEventListener('change', function () {
            handleFiles(this.files);
        });

        function handleFiles(files) {
            if (files.length > 0) {
                uploadFile(files[0]);
            }
        }

        async function uploadFile(file) {
            const formData = new FormData();
            formData.append('file', file);

            // Show loader
            loader.classList.add('visible');
            resultsSection.classList.remove('active');

            try {
                const response = await fetch('/upload', {
                    method: 'POST',
                    body: formData
                });

                if (!response.ok) throw new Error('Upload failed');

                const data = await response.json();

                // Store for compare
                currentExtractionData = data.json;

                // Update UI
                resultImage.src = data.debug_image_url;
                jsonOutput.innerHTML = syntaxHighlight(data.json);
                resultsSection.classList.add('active');

            } catch (error) {
                console.error('Error:', error);
                alert('Error processing image: ' + error.message);
            } finally {
                loader.classList.remove('visible');
            }
        }

        async function runComparison() {
            const compareInput = document.getElementById('compareInput');
            if (!compareInput.files.length) {
                alert("Please upload a Ground Truth JSON file first.");
                return;
            }
            if (!currentExtractionData) {
                alert("Please extract an image first in the Extract tab.");
                return;
            }

            const formData = new FormData();
            formData.append('ground_truth', compareInput.files[0]);
            formData.append('extracted_data', JSON.stringify(currentExtractionData));

            loader.classList.add('visible');

            try {
                const response = await fetch('/compare', { method: 'POST', body: formData });
                if (!response.ok) throw new Error("Comparison failed");
                const diff = await response.json();

                renderDiff(diff);

            } catch (e) {
                alert(e.message);
            } finally {
                loader.classList.remove('visible');
            }
        }

        function renderDiff(diff) {
            const container = document.getElementById('compareResults');
            container.innerHTML = ""; // Clear

            // Helper to create box
            function createBox(title, items, type, renderer) {
                const box = document.createElement('div');
                box.className = 'diff-box';
                const h = document.createElement('div');
                h.className = 'diff-title';
                h.innerText = title + ` (${items.length})`;
                box.appendChild(h);

                const list = document.createElement('div');
                list.className = 'diff-list';

                if (items.length === 0) {
                    const empty = document.createElement('div');
                    empty.className = 'diff-item';
                    empty.style.color = '#94a3b8';
                    empty.innerText = "None";
                    list.appendChild(empty);
                } else {
                    items.forEach(item => {
                        const div = document.createElement('div');
                        div.className = `diff-item ${type}`;
                        div.innerHTML = renderer(item);
                        list.appendChild(div);
                    });
                }
                box.appendChild(list);
                return box;
            }

            // RENDERERS
            const entityRenderer = (e) => `<strong>${e.name}</strong> <span style="font-size:0.8em;opacity:0.7">(${e.id})</span>`;
            const relRenderer = (r) => `${r.from} &rarr; ${r.to} <span style="opacity:0.7">(${r.percentage || ''})</span>`;
            const mismatchRenderer = (m) => `<strong>${m.from} &rarr; ${m.to}</strong><br>Src: ${m.source_ownership} | Ext: ${m.extracted_ownership}`;

            // 1. Entities Row
            const row1 = document.createElement('div');
            row1.style.display = 'flex';
            row1.style.gap = '1rem';
            row1.innerHTML = "<h3 style='width:100%; margin-bottom:0.5rem; color:var(--text-sub)'>Entities</h3>";
            row1.style.flexWrap = "wrap";

            container.appendChild(row1);
            container.appendChild(createBox("Missing Entities (In Source)", diff.entities.missing, "diff-missing", entityRenderer));
            container.appendChild(createBox("Extra Entities (In Extracted)", diff.entities.extra, "diff-unexpected", entityRenderer));

            // 2. Relationships Row
            const row2 = document.createElement('div');
            row2.style.marginTop = "2rem"; // Removed flex from label row to keep it clean
            row2.innerHTML = "<h3 style='width:100%; margin-bottom:0.5rem; color:var(--text-sub)'>Relationships</h3>";
            container.appendChild(row2);

            const relContainer = document.createElement('div');
            relContainer.className = 'rel-row';

            relContainer.appendChild(createBox("Missing Links", diff.relationships.missing, "diff-missing", relRenderer));
            relContainer.appendChild(createBox("Extra Links", diff.relationships.extra, "diff-unexpected", relRenderer));
            relContainer.appendChild(createBox("Ownership Mismatches", diff.relationships.mismatched_ownership, "diff-mismatch", mismatchRenderer));

            container.appendChild(relContainer);
        }

        function syntaxHighlight(json) {
            if (typeof json != 'string') {
                json = JSON.stringify(json, undefined, 2);
            }
            json = json.replace(/&/g, '&amp;').replace(/</g, '&lt;').replace(/>/g, '&gt;');
            return json.replace(/("(\\u[a-zA-Z0-9]{4}|\\[^u]|[^\\"])*"(\s*:)?|\b(true|false|null)\b|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?)/g, function (match) {
                var cls = 'number';
                if (/^"/.test(match)) {
                    if (/:$/.test(match)) {
                        cls = 'key';
                    } else {
                        cls = 'string';
                    }
                } else if (/true|false/.test(match)) {
                    cls = 'boolean';
                } else if (/null/.test(match)) {
                    cls = 'null';
                }
                return '<span class="' + cls + '">' + match + '</span>';
            });
        }
    </script>
</body>

</html>""";
return ResponseEntity.ok().contentType(MediaType.TEXT_HTML).body(html);
    }
}

