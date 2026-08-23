/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

import { getMcVersion } from "./mc_version.js"

const buildNumber = process.env.GITHUB_RUN_NUMBER ?? "local";

console.log("number=" + buildNumber)
